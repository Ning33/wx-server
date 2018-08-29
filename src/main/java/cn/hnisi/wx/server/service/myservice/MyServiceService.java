package cn.hnisi.wx.server.service.myservice;

import cn.hnisi.wx.server.service.model.Order;

import java.util.List;

public interface MyServiceService {
    /**
     * 根据微信userId 查询所有事项
     * @param userId
     * @return
     */
    List<Order> searchServices(String userId ,String status);

    /**
     * 根据orderNo查询事项
     * @param orderNo
     * @return
     */
    Order queryMyServiceByOrderNo(String orderNo);
}
