package cn.hnisi.wx.server.security;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.security.SecurityService;
import cn.hnisi.wx.core.security.User;
import cn.hnisi.wx.core.wxapi.IWxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SecurityServiceImpl extends SecurityService {

    @Resource
    private IWxService wxService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

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
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            redisTemplate.opsForValue().set(user.getSessionid(),objectMapper.writeValueAsString(user),2, TimeUnit.HOURS);
        } catch (JsonProcessingException e) {
            throw new AppException(e);
        }
    }

    @Override
    public User getUser(String sessionid) {
        try {
            return new ObjectMapper().readValue(redisTemplate.opsForValue().get(sessionid),User.class);
        } catch (IOException e) {
           throw new AppException(e);
        }
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
