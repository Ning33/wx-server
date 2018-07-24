package cn.hnisi.wx.server.io;

public enum ResponseStatus{
    OK("1"),
    JS_CODE_INVALID("1001","js_code is invalid"),
    SESSION_WX_EXPIRED("1002","sessionKey is expired"),
    SESSION_GATEWAY_EXPIRED("1003","sessionid is expired"),
    DATA_VALIDATE_EXCEPTION("2001","data validate error"),
    UNKNOWN_ERROR("9999","unknown error");

    private String errcode;
    private String errmsg;

    ResponseStatus(String errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    ResponseStatus(String errcode) {
        this.errcode = errcode;
    }

    @Override
    public String toString() {
        return this.errcode;
    }

    public static ResponseStatus valueFrom(String errcode){
        for(ResponseStatus responseStatus : values()){
            if(responseStatus.getErrcode().equals(errcode)){
                return responseStatus;
            }
        }
        throw new IllegalArgumentException("errcode is not match,errcode: "+errcode);
    }

    public String getErrcode() {
        return errcode;
    }

    public String getErrmsg(){
        return errmsg;
    }
}
