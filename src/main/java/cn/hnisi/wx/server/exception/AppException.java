package cn.hnisi.wx.server.exception;

import cn.hnisi.wx.server.io.ResponseStatus;
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
