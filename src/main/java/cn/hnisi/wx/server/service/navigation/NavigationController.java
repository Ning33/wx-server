package cn.hnisi.wx.server.service.navigation;


import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.server.service.navigation.model.ServiceItem;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/api/frontend/serviceItems/queryAllItems")
    public ResponseEntity<ServiceItem> queryAllItems(){
        List<ServiceItem> items =  navigationService.queryAllItems();
        return new ResponseEntity(items);
    }

    /**
     * 根据title模糊查询事项
     * @param title
     * @return
     */
    @RequestMapping("/api/frontend/serviceItems/queryItemByTitle")
    public ResponseEntity<ServiceItem> queryItemByTitle(String title){
        List<ServiceItem> items =  navigationService.queryItemByTitle(title);
        return new ResponseEntity(items);
    }
}
