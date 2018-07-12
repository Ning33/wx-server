package cn.hnisi.wx.core.security;

public interface ISecurityService {

    /**
     * 登录
     * 通过微信的jsCode登录，获取用户信息，
     * 用户信息中包含sessionid，用于网关识别已登录用户身份
     * @param jsCode 微信客户端给出的令牌，用于向微信服务器查询openid和sessionKey
     * @return
     */
    User login(String jsCode);

    /**
     * 登录
     * 具体实现类推荐实现此方法，自动注入appid和secret
     * @param jsCode
     * @param appid
     * @param secret
     * @return
     */
    User login(String jsCode, String appid, String secret);

    /**
     * 存储用户信息
     * @param user
     * @return sessionid
     */
    String storeUser(User user);

    /**
     * 获取用户信息
     * @param sessionid
     * @return
     */
    User getUser(String sessionid);

    /**
     * 查询用户个人身份信息
     * @param openid
     * @return
     */
    User queryUserPersonalInfo(String openid);

    /**
     * 注入用户个人身份信息
     * 抽象类已实现此方法，不建议重复实现
     * @param user
     * @return
     */
    boolean injectUserPersonalInfo(User user);

    /**
     * 绑定用户个人身份信息
     * @param user
     * @return
     */
    User bindUser(User user);

    /**
     * 取消绑定用户个人身份信息
     * @param user
     * @return
     */
    boolean unbindUser(User user);


}
