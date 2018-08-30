package cn.hnisi.wx.server.service.navigation.dao;

import cn.hnisi.wx.server.service.navigation.model.ServiceCatalog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceCatalogDAO {
    /**
     * 查询所有分类
     */
    List<ServiceCatalog> queryAllCatalog();
}
