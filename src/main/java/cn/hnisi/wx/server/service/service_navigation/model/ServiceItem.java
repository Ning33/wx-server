package cn.hnisi.wx.server.service.service_navigation.model;

/**
 * 事项导航 实体类
 */
public class ServiceItem {

    private String serviceId;     //事项id
    private String name;    //事项名称
    private String title;   //事项标题
    private String description; //事项内容
    private String catalog;    //事项类别: bxgx-征缴业务,yldy-养老待遇,gsdy-工伤待遇,syedy-失业待遇,yildy-医疗待遇
    private String securityLevel; //安全级别
    private int orderNum ; //排序

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
}
