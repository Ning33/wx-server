package cn.hnisi.wx.server.service.yldyhd.model;

import cn.hnisi.wx.core.utils.JsonUtil;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(JsonUtil.UpperCaseStrategy.class)
public class Ffzhqr {
    //开户名
    private String AAE133;
    //银行账号
    private String AAE010;
    //开户银行
    private String AAE195;

    public String getAAE133() {
        return AAE133;
    }

    public void setAAE133(String AAE133) {
        this.AAE133 = AAE133;
    }

    public String getAAE010() {
        return AAE010;
    }

    public void setAAE010(String AAE010) {
        this.AAE010 = AAE010;
    }

    public String getAAE195() {
        return AAE195;
    }

    public void setAAE195(String AAE195) {
        this.AAE195 = AAE195;
    }
}
