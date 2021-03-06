package cn.hnisi.wx.server.service.navigation.impl;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.server.service.navigation.NavigationService;
import cn.hnisi.wx.server.service.navigation.dao.ServiceCatalogDAO;
import cn.hnisi.wx.server.service.navigation.dao.ServiceItemDAO;
import cn.hnisi.wx.server.service.navigation.model.ServiceCatalog;
import cn.hnisi.wx.server.service.navigation.model.ServiceItem;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NavigationServiceImpl implements NavigationService {

    @Resource
    private ServiceItemDAO serviceItemDAO;

    @Resource
    private ServiceCatalogDAO serviceCatalogDAO;

    @Override
    public List<ServiceItem> queryAllItems() {
        return serviceItemDAO.queryAllItems();
    }

    @Override
    public List<ServiceItem> queryItemByTitle(String title) {
        if(StringUtils.isEmpty(title)){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"数据不能为空");
        }
        List<ServiceItem> items = serviceItemDAO.queryItemByTitle(title);
        return items;
    }

    @Override
    public List<ServiceCatalog> queryAllCatalog() {
        return serviceCatalogDAO.queryAllCatalog();
    }
}
