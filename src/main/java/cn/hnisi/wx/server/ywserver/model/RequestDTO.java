package cn.hnisi.wx.server.ywserver.model;

public class RequestDTO{

    private String orderNo;
    private String idcard;
    private String name;
    private ExchangeData requestData;
    private ExtraData extraData;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public ExchangeData getRequestData() {
        return requestData;
    }

    public void setRequestData(ExchangeData requestData) {
        this.requestData = requestData;
    }

    public ExtraData getExtraData() {
        return extraData;
    }

    public void setExtraData(ExtraData extraData) {
        this.extraData = extraData;
    }
}

