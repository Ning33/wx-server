package cn.hnisi.wx.server.security.model;

import cn.hnisi.wx.server.person.model.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 操作员
 */
public class User extends Person {

    /*用户ID*/
    private String userid;
    /**
     * 用户唯一标识
     */
    private String openid;
    /**
     * 调用微信接口使用的session
     */
    private String session_key;
    /**
     * 网关发布的凭证，用户识别依据
     */
    private String sessionid;

    @JsonProperty("isBoundIdcard")
    public boolean isBoundIdcard() {
        String idcard = getIdcard();
        return idcard != null && !idcard.equals("");
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
