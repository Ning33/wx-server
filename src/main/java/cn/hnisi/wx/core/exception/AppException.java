package cn.hnisi.wx.core.exception;

import cn.hnisi.wx.core.io.ResponseStatus;
import org.springframework.util.StringUtils;

public class AppException extends RuntimeException {

    private String errcode;
    private String errmsg;

    public AppException(String errcode,String errmsg){
        super(errmsg);
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public AppException(ResponseStatus responseStatus, String errmsg){
        super(StringUtils.isEmpty(errmsg)?responseStatus.getErrmsg():errmsg);
        this.errcode = responseStatus.toString();
        this.errmsg = errmsg;
    }

    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
