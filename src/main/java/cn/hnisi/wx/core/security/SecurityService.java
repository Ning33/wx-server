package cn.hnisi.wx.core.security;


import cn.hnisi.wx.core.WxProperties;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

public abstract class SecurityService implements ISecurityService{

    @Resource
    private WxProperties wxProperties;

    @Override
    public User login(String jsCode) {
        String appid = wxProperties.getAppid();
        String secret = wxProperties.getSecret();
        System.out.println(wxProperties);
        return login(jsCode,appid,secret);
    }

    @Override
    public boolean injectUserPersonalInfo(User user){
        User personalInfo = queryUserPersonalInfo(user.getOpenid());
        //如果用户尚未绑定，则直接返回
        if(!personalInfo.isBound()){
            return false;
        }

        user.setIdcard(user.getIdcard());
        BeanUtils.copyProperties(personalInfo,user);
        return true;
    }
}
