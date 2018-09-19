package cn.hnisi.wx.server.service.myservice;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.utils.RequestBodyParam;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.model.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * 我的事项 控制层
 */
@RestController
public class MyServiceController {

    @Resource
    private MyServiceService myServiceService;

    /**
     * 查询办理中的事项
     * @param user
     * @return
     */
    @PostMapping("/api/frontend/myservice/searchServices")
    public ResponseEntity<List<Order>> searchReviewService(User user , @RequestBodyParam String status){

       List<Order> items =  myServiceService.searchServices(user.getUserId() , status);
       return new ResponseEntity<>(items);
    }

    /**
     * 根据受理单号查询事项
     * @param orderNo
     * @return
     */
    @PostMapping("/api/frontend/myservice/queryMyServiceByOrderNo")
    public ResponseEntity<Order> queryMyServiceByOrderNo(@RequestBodyParam String orderNo){
        Order order = myServiceService.queryMyServiceByOrderNo(orderNo);
        return new ResponseEntity<>(order);
    }
}
