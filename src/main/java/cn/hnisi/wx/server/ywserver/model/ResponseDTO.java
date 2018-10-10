package cn.hnisi.wx.server.ywserver.model;

public class ResponseDTO{
    private String orderNo;
    private boolean isSuccess;
    private String remark;
    private ExchangeData requestData;
    private ExchangeData responseData;
    private String bae007;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ExchangeData getRequestData() {
        return requestData;
    }

    public void setRequestData(ExchangeData requestData) {
        this.requestData = requestData;
    }

    public ExchangeData getResponseData() {
        return responseData;
    }

    public void setResponseData(ExchangeData responseData) {
        this.responseData = responseData;
    }

    public String getBae007() {
        return bae007;
    }

    public void setBae007(String bae007) {
        this.bae007 = bae007;
    }
}
