package cn.hnisi.wx.core.utils;

/**
 * 描述状态类
 * ALL --- 任何人都可以办理
 * LOGIN --- 登录系统即可
 * REAL_NAME --- 完成实名认证
 * VALIDATE_FACE --- 人脸识别有效期内才可以
 */
public enum SecurityLevel {

    ALL("0"),
    LOGIN("1"),
    REAL_NAME("2"),
    VALIDATE_FACE("3");

    private String status;
    SecurityLevel(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
