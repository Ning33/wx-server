package cn.hnisi.wx.server.service.navigation;

import cn.hnisi.wx.server.service.navigation.model.ServiceItem;

import java.util.List;

public interface NavigationService {

    /**
     * 查询所有业务事项
     * @return
     */
    List<ServiceItem> queryAllItems();

    /**
     * 根据名称模糊查询业务事项
     * @param title
     * @return
     */
    List<ServiceItem> queryItemByTitle( String title);
}
