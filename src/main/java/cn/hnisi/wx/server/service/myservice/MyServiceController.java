package cn.hnisi.wx.server.service.myservice;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.model.Order;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping("/api/frontend/myservice/searchServices")
    public ResponseEntity<Order> searchReviewService( User user , String status){

        //进行测试
        //TODO
       List<Order> items =  myServiceService.searchServices("7eadc26aa1fa4c01b695ac7eeff58e1d" , status);
       return new ResponseEntity(items);
    }

    /**
     * 根据受理单号查询事项
     * @param orderno
     * @return
     */
    @RequestMapping("/api/frontend/myservice/queryMyServiceByOrderNo")
    public ResponseEntity<Order> queryMyServiceByOrderNo(String orderno){
        Order order = myServiceService.queryMyServiceByOrderNo(orderno);
        return new ResponseEntity(order);
    }
}
