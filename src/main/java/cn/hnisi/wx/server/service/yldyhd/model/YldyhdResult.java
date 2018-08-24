package cn.hnisi.wx.server.service.yldyhd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YldyhdResult {


    private String BAE007;
    private Cbqkqr cbqk;
    private Ffzhqr ffzh;
    private Sbxx sbxx;
    private Hdjg hdjg;

    @JsonProperty("BAE007")
    public String getBAE007() {
        return BAE007;
    }
    @JsonProperty("BAE007")
    public void setBAE007(String BAE007) {
        this.BAE007 = BAE007;
    }

    public Cbqkqr getCbqk() {
        return cbqk;
    }

    public void setCbqk(Cbqkqr cbqk) {
        this.cbqk = cbqk;
    }

    public Ffzhqr getFfzh() {
        return ffzh;
    }

    public void setFfzh(Ffzhqr ffzh) {
        this.ffzh = ffzh;
    }

    public Sbxx getSbxx() {
        return sbxx;
    }

    public void setSbxx(Sbxx sbxx) {
        this.sbxx = sbxx;
    }

    public cn.hnisi.wx.server.service.yldyhd.model.Hdjg getHdjg() {
        return hdjg;
    }

    public void setHdjg(cn.hnisi.wx.server.service.yldyhd.model.Hdjg hdjg) {
        this.hdjg = hdjg;
    }
}
