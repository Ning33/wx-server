package cn.hnisi.wx.server.service.model;

/**
 * 业务状态
 */
public enum OrderStatus{
    APPLY("0"),REVIEW("10"),SUCCESS("21"),FAIL("22");

    private String key;
    OrderStatus(String key){
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }
}