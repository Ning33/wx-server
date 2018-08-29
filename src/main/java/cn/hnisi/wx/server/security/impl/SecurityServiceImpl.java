package cn.hnisi.wx.server.security.impl;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.utils.GuidUtil;
import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.server.person.PersonService;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.BaseSecurityService;
import cn.hnisi.wx.server.security.SecurityService;
import cn.hnisi.wx.server.security.dao.UserDAO;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.wxapi.IWxService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class SecurityServiceImpl extends BaseSecurityService implements SecurityService {

    @Resource
    private IWxService wxService;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Resource
    private UserDAO userDAO;

    @Resource
    private PersonService personService;

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

        //注入用户基本信息
        injectPerson(user);

        //存储用户信息至用户中心，目前使用redis管理
        storeUser(user);
        return user;
    }

    private String initSessionid(){
        return GuidUtil.generate();
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
    protected User queryUserInfo(String openid) {
        User user = userDAO.query(openid);
        if(user == null){
            return null;
        }

        Person selfPerson = personService.querySelf(user.getUserId());

        BeanUtils.copyProperties(selfPerson,user,Person.class);

        return user;
    }

    @Override
    @Transactional
    public User register(User user) {

        //查询用户信息表，校验是否允许注册
        User queryUser = userDAO.query(user.getOpenid());
        if(queryUser != null){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"用户已注册");
        }

        //注册用户信息
        user.setUserId(GuidUtil.generate());
        userDAO.insert(user);

        //同步生成人员信息
        user.setIsSelf(true);
        personService.bind(user,user);

        //注入人员信息
        injectPerson(user);

        //回写至redis中
        storeUser(user);

        return user;
    }

    @Override
    public boolean unregister(User user) {
        return false;
    }
}
