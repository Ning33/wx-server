package cn.hnisi.wx.server.service.navigation;


import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.utils.RequestBodyParam;
import cn.hnisi.wx.server.service.navigation.model.ServiceCatalog;
import cn.hnisi.wx.server.service.navigation.model.ServiceItem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class NavigationController {

    @Resource
    private NavigationService navigationService;

    /**
     * 查询所有事项
     * @return
     */
    @PostMapping("/api/frontend/serviceItems/queryAllItems")
    public ResponseEntity<List<ServiceItem>> queryAllItems(){
        List<ServiceItem> items =  navigationService.queryAllItems();
        return new ResponseEntity<>(items);
    }

    /**
     * 根据title模糊查询事项
     * @param title
     * @return
     */
    @PostMapping("/api/frontend/serviceItems/queryItemByTitle")
    public ResponseEntity<List<ServiceItem>> queryItemByTitle(@RequestBodyParam String title){
        List<ServiceItem> items =  navigationService.queryItemByTitle(title);
        return new ResponseEntity<>(items);
    }

    @PostMapping("/api/frontend/serviceItems/queryAllCatalog")
    public ResponseEntity<List<ServiceCatalog>> queryAllServiceCatalog(){
        List<ServiceCatalog> items =  navigationService.queryAllCatalog();
        return new ResponseEntity<>(items);
    }
}
