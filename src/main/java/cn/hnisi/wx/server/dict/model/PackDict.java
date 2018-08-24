package cn.hnisi.wx.server.dict.model;

/**
 * 封装字典
 */
public class PackDict {
    private boolean needUpdate; //是否需要更新字典
    private Object data;    //数据

    public boolean isNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
