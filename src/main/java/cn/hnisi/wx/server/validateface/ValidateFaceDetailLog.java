package cn.hnisi.wx.server.validateface;

import java.sql.Date;

/**
 * 人脸详细日志实体类
 */
public class ValidateFaceDetailLog {

    private String idcard;  //身份ID
    private String name;    //姓名
    private String token;   //凭证
    private String data;    //数据
    private int exist;      //是否存入明细数据 1:已存,0:未存
    private String pic_1;   //图1
    private String pic_2;   //图2
    private String pic_3;   //图3
    private String video;   //视频
    private String machine; //机器标志
    private Date logTime;   //日志时间

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPic_1() {
        return pic_1;
    }

    public void setPic_1(String pic_1) {
        this.pic_1 = pic_1;
    }

    public String getPic_2() {
        return pic_2;
    }

    public void setPic_2(String pic_2) {
        this.pic_2 = pic_2;
    }

    public String getPic_3() {
        return pic_3;
    }

    public void setPic_3(String pic_3) {
        this.pic_3 = pic_3;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    @Override
    public String toString() {
        return "ValidateFaceDetailLog{" +
                "idcard='" + idcard + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", data='" + data + '\'' +
                ", exist=" + exist +
                ", pic_1='" + pic_1 + '\'' +
                ", pic_2='" + pic_2 + '\'' +
                ", pic_3='" + pic_3 + '\'' +
                ", video='" + video + '\'' +
                ", machine='" + machine + '\'' +
                ", logTime=" + logTime +
                '}';
    }
}
