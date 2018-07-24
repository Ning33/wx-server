package cn.hnisi.wx.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("properties.validate-face")
public class ValidateFaceProperties {

    private String appid;
    private String secretKey;
    private String resultKey;
    private int expired;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getResultKey() {
        return resultKey;
    }

    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }
}
