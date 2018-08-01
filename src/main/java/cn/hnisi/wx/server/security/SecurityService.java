package cn.hnisi.wx.server.security;

import cn.hnisi.wx.server.security.model.User;

public interface SecurityService {

    /**
     * 登录
     * 通过微信的jsCode登录，获取用户信息，
     * 用户信息中包含sessionid，用于网关识别已登录用户身份
     * @param jsCode 微信客户端给出的令牌，用于向微信服务器查询openid和sessionKey
     * @return
     */
    User login(String jsCode);

    /**
     * 存储用户信息
     * @param user
     * @return sessionid
     */
    void storeUser(User user);

    /**
     * 获取用户信息
     * @param sessionid
     * @return
     */
    User getUser(String sessionid);

    /**
     * 注册新用户
     * @param user
     * @return
     */
    User register(User user);

    /**
     * 注销用户
     * @param user
     * @return
     */
    boolean unregister(User user);


}
