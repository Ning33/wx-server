package cn.hnisi.wx.core.exception;

import cn.hnisi.wx.core.io.ResponseStatus;

public class DataValidationException extends AppException{

    public DataValidationException(String errmsg){
        super(ResponseStatus.DATA_VALIDATE_EXCEPTION,errmsg);
    }

    public DataValidationException(String errmsg,Throwable cause){
        super(ResponseStatus.DATA_VALIDATE_EXCEPTION,errmsg,cause);
    }
}
