package cn.hnisi.wx.server.service.yldyhd.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YldyhdResult {


    private String BAE007;
    private CbqkqrResponse cbqk;
    private FfzhqrResponse ffzh;
    private SbxxResponse sbxx;
    private Hdjg hdjg;

    @JsonProperty("BAE007")
    public String getBAE007() {
        return BAE007;
    }
    @JsonProperty("BAE007")
    public void setBAE007(String BAE007) {
        this.BAE007 = BAE007;
    }

    public CbqkqrResponse getCbqk() {
        return cbqk;
    }

    public void setCbqk(CbqkqrResponse cbqk) {
        this.cbqk = cbqk;
    }

    public FfzhqrResponse getFfzh() {
        return ffzh;
    }

    public void setFfzh(FfzhqrResponse ffzh) {
        this.ffzh = ffzh;
    }

    public SbxxResponse getSbxx() {
        return sbxx;
    }

    public void setSbxx(SbxxResponse sbxx) {
        this.sbxx = sbxx;
    }

    public cn.hnisi.wx.server.service.yldyhd.model.Hdjg getHdjg() {
        return hdjg;
    }

    public void setHdjg(cn.hnisi.wx.server.service.yldyhd.model.Hdjg hdjg) {
        this.hdjg = hdjg;
    }
}
