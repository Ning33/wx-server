package cn.hnisi.wx.server.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("properties.ftp-upload")
@Component
public class FtpProperties {

    private String username;
    private String password;
    private String hostname;
    private int port;
    private String path_ftp;
    private String imgPath_suf_jpg;
    private String videoPath_suf;


    public String getPath_ftp() {
        return path_ftp;
    }

    public void setPath_ftp(String path_ftp) {
        this.path_ftp = path_ftp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getImgPath_suf_jpg() {
        return imgPath_suf_jpg;
    }

    public void setImgPath_suf_jpg(String imgPath_suf_jpg) {
        this.imgPath_suf_jpg = imgPath_suf_jpg;
    }

    public String getVideoPath_suf() {
        return videoPath_suf;
    }

    public void setVideoPath_suf(String videoPath_suf) {
        this.videoPath_suf = videoPath_suf;
    }
}
