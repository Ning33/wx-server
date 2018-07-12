package cn.hnisi.wx.core.security;

import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl extends SecurityService {
    @Override
    public User login(String jsCode, String appid, String secret) {
        return null;
    }

    @Override
    public String storeUser(User user) {
        return null;
    }

    @Override
    public User getUser(String sessionid) {
        return null;
    }

    @Override
    public User queryUserPersonalInfo(String openid) {
        return null;
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
