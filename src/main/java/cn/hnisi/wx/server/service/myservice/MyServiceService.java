package cn.hnisi.wx.server.service.myservice;

import cn.hnisi.wx.server.service.model.Order;

import java.util.List;

public interface MyServiceService {
    /**
     * 根据微信userid 查询所有事项
     * @param userid
     * @return
     */
    List<Order> searchServices(String userid ,String status);

    /**
     * 根据orderno查询事项
     * @param orderno
     * @return
     */
    Order queryMyServiceByOrderNo(String orderno);
}
