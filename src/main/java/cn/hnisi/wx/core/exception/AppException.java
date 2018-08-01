package cn.hnisi.wx.core.exception;

import cn.hnisi.wx.core.io.ResponseStatus;
import org.springframework.util.StringUtils;

public class AppException extends RuntimeException {

    private ResponseStatus status;
    private String errmsg;

    public AppException(ResponseStatus responseStatus){
        super(responseStatus.getErrmsg());
        this.status = responseStatus;
    }

    public AppException(ResponseStatus responseStatus, String errmsg){
        super(StringUtils.isEmpty(errmsg)?responseStatus.getErrmsg():errmsg);
        this.status = responseStatus;
        this.errmsg = errmsg;
    }

    public AppException(ResponseStatus responseStatus, String errmsg,Throwable cause){
        super(StringUtils.isEmpty(errmsg)?responseStatus.getErrmsg():errmsg,cause);
        this.status = responseStatus;
        this.errmsg = errmsg;
    }

    public AppException() {
        super();
        this.status = ResponseStatus.UNKNOWN_ERROR;
    }

    public AppException(String message) {
        super(message);
        this.status = ResponseStatus.UNKNOWN_ERROR;
        this.errmsg = message;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        this.status = ResponseStatus.UNKNOWN_ERROR;
        this.errmsg = message;
    }

    public AppException(Throwable cause) {
        super(cause);
        this.status = ResponseStatus.UNKNOWN_ERROR;
        this.errmsg = cause.getMessage();
    }

    public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = ResponseStatus.UNKNOWN_ERROR;
        this.errmsg = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
