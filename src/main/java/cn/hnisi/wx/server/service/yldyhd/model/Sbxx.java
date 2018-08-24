package cn.hnisi.wx.server.service.yldyhd.model;

import cn.hnisi.wx.core.utils.JsonUtil;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(JsonUtil.UpperCaseStrategy.class)
public class Sbxx {

    /*个人信息*/
    //姓名
    private String AAC003;
    //身份证号
    private String AAC002;
    //社保卡号
    private String AAE010;

    /*基本信息*/
    private String CAC031;
    private String CAC542;
    private String CIC520;

    /*联系方式*/
    private String CAC554;
    private String AAE005;
    private String CAC552;
    private String CAC551;

    public String getAAC003() {
        return AAC003;
    }

    public void setAAC003(String AAC003) {
        this.AAC003 = AAC003;
    }

    public String getAAC002() {
        return AAC002;
    }

    public void setAAC002(String AAC002) {
        this.AAC002 = AAC002;
    }

    public String getAAE010() {
        return AAE010;
    }

    public void setAAE010(String AAE010) {
        this.AAE010 = AAE010;
    }

    public String getCAC031() {
        return CAC031;
    }

    public void setCAC031(String CAC031) {
        this.CAC031 = CAC031;
    }

    public String getCAC542() {
        return CAC542;
    }

    public void setCAC542(String CAC542) {
        this.CAC542 = CAC542;
    }

    public String getCIC520() {
        return CIC520;
    }

    public void setCIC520(String CIC520) {
        this.CIC520 = CIC520;
    }

    public String getCAC554() {
        return CAC554;
    }

    public void setCAC554(String CAC554) {
        this.CAC554 = CAC554;
    }

    public String getAAE005() {
        return AAE005;
    }

    public void setAAE005(String AAE005) {
        this.AAE005 = AAE005;
    }

    public String getCAC552() {
        return CAC552;
    }

    public void setCAC552(String CAC552) {
        this.CAC552 = CAC552;
    }

    public String getCAC551() {
        return CAC551;
    }

    public void setCAC551(String CAC551) {
        this.CAC551 = CAC551;
    }
}
