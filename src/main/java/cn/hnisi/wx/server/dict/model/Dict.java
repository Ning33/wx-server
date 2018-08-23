package cn.hnisi.wx.server.dict.model;

/**
 * 字典类
 */
public class Dict {
    private String field;  //字段名称
    private String dictValue;   //字段实际值
    private String dictDisplay; //字段显示值
    private String version;    //版本号

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictDisplay() {
        return dictDisplay;
    }

    public void setDictDisplay(String dictDisplay) {
        this.dictDisplay = dictDisplay;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
