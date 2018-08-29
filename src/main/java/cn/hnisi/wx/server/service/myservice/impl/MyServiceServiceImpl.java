package cn.hnisi.wx.server.service.myservice.impl;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.server.service.model.Order;
import cn.hnisi.wx.server.service.myservice.MyServiceService;
import cn.hnisi.wx.server.service.myservice.dao.MyServiceDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MyServiceServiceImpl implements MyServiceService {

    @Resource
    private MyServiceDao myServiceDao;

    @Override
    public List<Order> searchServices(String userId , String status) {
        //检验userId
        if(StringUtils.isEmpty(userId)){
            throw new AppException(ResponseStatus.UNBOUND_USER);
        }
        List<Order> items = myServiceDao.searchServices(userId , status);

        return items;
    }

    @Override
    public Order queryMyServiceByOrderNo(String orderNo) {
        //检验userId
        if(StringUtils.isEmpty(orderNo)){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION);
        }

        Order order = myServiceDao.queryMyServiceByOrderNo(orderNo);

        if(order == null){
            throw new AppException(ResponseStatus.UNKNOWN_ERROR,"SORRY UNKNOWN_ERROR");
        }
        return order;
    }
}
