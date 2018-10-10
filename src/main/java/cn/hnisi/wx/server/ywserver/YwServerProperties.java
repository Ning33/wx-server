package cn.hnisi.wx.server.ywserver;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("properties.yw-server")
public class YwServerProperties {
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
