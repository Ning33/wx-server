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
    public List<Order> searchServices(String userid , String status) {
        //检验userid
        if(StringUtils.isEmpty(userid)){
            throw new AppException(ResponseStatus.UNBOUND_USER);
        }
        List<Order> items = myServiceDao.searchServices(userid , status);

        return items;
    }

    @Override
    public Order queryMyServiceByOrderNo(String orderno) {
        //检验userid
        if(StringUtils.isEmpty(orderno)){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION);
        }

        Order order = myServiceDao.queryMyServiceByOrderNo(orderno);

        if(order == null){
            throw new AppException(ResponseStatus.UNKNOWN_ERROR,"SORRY UNKNOWN_ERROR");
        }
        return order;
    }
}
