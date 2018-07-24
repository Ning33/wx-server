package cn.hnisi.wx.server.security;


import org.springframework.beans.BeanUtils;

public abstract class SecurityService implements ISecurityService{

    @Override
    public boolean injectUserPersonalInfo(User user){
        User personalInfo = queryUserPersonalInfo(user.getOpenid());
        //如果用户尚未绑定，则直接返回
        if(!personalInfo.isBound()){
            return false;
        }
        String[] ignoreProperties = new String[]{"appid","openid","session_key","sessionid"};
        BeanUtils.copyProperties(personalInfo,user, ignoreProperties);
        return true;
    }
}
