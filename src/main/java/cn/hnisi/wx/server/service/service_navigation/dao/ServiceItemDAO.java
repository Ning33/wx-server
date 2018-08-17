package cn.hnisi.wx.server.service.service_navigation.dao;

import cn.hnisi.wx.server.service.service_navigation.model.ServiceItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ServiceItemDAO {
    /**
     * 查询所有业务事项
     */
    List<ServiceItem> queryAllItems();

    /**
     * 根据名称模糊查询业务事项
     * @param title
     * @return
     */
    List<ServiceItem> queryItemByTitle(@Param("title") String title);

    /**
     * 根据serviceName查询数据, 查看安全级别 或者有无此事项
     * @param serviceName
     * @return
     */
    String queryByServiceName(@Param("serviceName") String serviceName);
}
