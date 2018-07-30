package cn.hnisi.wx.server.security;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.server.wxapi.IWxService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SecurityServiceImpl extends SecurityService {

    @Resource
    private IWxService wxService;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Override
    public User login(String jsCode) throws AppException {
        //调用微信接口，换取微信身份信息
        User user = new User();
        try{
            IWxService.Jscode2sessionResponse response = wxService.jscode2session(jsCode);
            BeanUtils.copyProperties(response,user);
        }catch(Exception ex){
            throw new AppException(ResponseStatus.JS_CODE_INVALID,"jsCode:"+jsCode+";"+ex.getMessage());
        }

        //生成sessionid
        String sessionid = initSessionid();
        user.setSessionid(sessionid);

        //调用业务接口，注入个人基本信息
        injectUserPersonalInfo(user);
        //存储用户信息至用户中心，目前使用redis管理
        storeUser(user);
        return user;
    }

    private String initSessionid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Override
    public void storeUser(User user) {
        redisTemplate.opsForValue().set("sessionid:"+user.getSessionid(),JsonUtil.convertBeanToJson(user),2,TimeUnit.HOURS);
    }

    @Override
    public User getUser(String sessionid) {
        String jsonStr = redisTemplate.opsForValue().get("sessionid:"+sessionid);
        if(StringUtils.isEmpty(jsonStr)){
            throw new AppException(ResponseStatus.SESSION_GATEWAY_EXPIRED);
        }
        return JsonUtil.convertJsonToBean(jsonStr,User.class);
    }

    @Override
    public User queryUserPersonalInfo(String openid) {
        //TODO TEST
        User user = new User();
        user.setBound(true);
        user.setIdcard("444444444");
        user.setName("张三");
        user.setSex("1");
        user.setBirthday("2018-01-01");
        user.setAddress("sfdsf");
        user.setTel("15478456542");
        return user;
    }

    @Override
    public User bindUser(User user) {
        return null;
    }

    @Override
    public boolean unbindUser(User user) {
        return false;
    }

}
