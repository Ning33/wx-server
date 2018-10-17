package cn.hnisi.wx.server.service.grsfjy.model;

import cn.hnisi.wx.core.utils.JsonUtil;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming(JsonUtil.UpperCaseStrategy.class)
public class Sbxx {

    //姓名
    private String AAC003;
    //性别
    private String AAC004;
    //身份证号
    private String AAC002;
    //出生日期
    private String AAC006;
    //缴费工资
    private BigDecimal AAC040;
    //参保年月
    private String AAC049;
    //参保机构
    private String BAE001;
    //参保险种
    private String AAE140;

    public String getAAC003() {
        return AAC003;
    }

    public void setAAC003(String AAC003) {
        this.AAC003 = AAC003;
    }

    public String getAAC004() {
        return AAC004;
    }

    public void setAAC004(String AAC004) {
        this.AAC004 = AAC004;
    }

    public String getAAC002() {
        return AAC002;
    }

    public void setAAC002(String AAC002) {
        this.AAC002 = AAC002;
    }

    public String getAAC006() {
        return AAC006;
    }

    public void setAAC006(String AAC006) {
        this.AAC006 = AAC006;
    }

    public BigDecimal getAAC040() {
        return AAC040;
    }

    public void setAAC040(BigDecimal AAC040) {
        this.AAC040 = AAC040;
    }

    public String getAAC049() {
        return AAC049;
    }

    public void setAAC049(String AAC049) {
        this.AAC049 = AAC049;
    }

    public String getBAE001() {
        return BAE001;
    }

    public void setBAE001(String BAE001) {
        this.BAE001 = BAE001;
    }

    public String getAAE140() {
        return AAE140;
    }

    public void setAAE140(String AAE140) {
        this.AAE140 = AAE140;
    }

}
