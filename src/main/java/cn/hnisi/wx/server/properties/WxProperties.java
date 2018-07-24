package cn.hnisi.wx.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "properties.wx")
public class WxProperties {
    private String appid;
    private String secret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "WxProperties{" +
                "appid='" + appid + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
