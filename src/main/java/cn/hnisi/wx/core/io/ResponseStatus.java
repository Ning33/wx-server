package cn.hnisi.wx.core.io;

public enum ResponseStatus{
    OK("0"),
    JS_CODE_INVALID("1001","js_code无效"),
    SESSION_WX_EXPIRED("1002","微信登录失效，sessionKey过期"),
    SESSION_GATEWAY_EXPIRED("1003","网关sessionid无效"),
    VALIDATE_FACE_EXPIRED("1004","人脸识别token无效"),
    UNBOUND_USER("1005","未实名用户"),
    DATA_VALIDATE_EXCEPTION("2001","数据检验错误"),
    UNKNOWN_ERROR("9999","未知错误"),
    GENERATE_ImageToFTP("1005","上传FTP错误");

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
