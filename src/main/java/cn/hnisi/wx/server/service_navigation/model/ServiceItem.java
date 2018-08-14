package cn.hnisi.wx.server.service_navigation.model;

/**
 * 事项导航 实体类
 */
public class ServiceItem {

    private String itemId;     //事项id
    private String name;    //事项名称
    private String title;   //事项标题
    private String content; //事项内容
    private int itemType;    //事项类别，1-征缴业务 2-养老待遇 3-失业待遇 4-工伤待遇 5-医疗待遇

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
