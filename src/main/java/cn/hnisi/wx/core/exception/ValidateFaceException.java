package cn.hnisi.wx.core.exception;

import cn.hnisi.wx.core.io.ResponseStatus;

/**
 * 人脸识别过期
 * 额外传递身份号码和姓名字段
 */
public class ValidateFaceException extends AppException{

    private String idcard;
    private String name;

    public ValidateFaceException(String idcard,String name){
        super(ResponseStatus.VALIDATE_FACE_EXPIRED);
        this.idcard = idcard;
        this.name = name;
    }

    public ValidateFaceException(String idcard,String name,String errmsg){
        super(ResponseStatus.VALIDATE_FACE_EXPIRED,errmsg);
        this.idcard = idcard;
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public String getName() {
        return name;
    }
}
