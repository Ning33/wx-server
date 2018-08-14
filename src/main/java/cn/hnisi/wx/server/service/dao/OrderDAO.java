package cn.hnisi.wx.server.service.dao;

import cn.hnisi.wx.server.service.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDAO {

    void insert(Order order);

    void updateStatus(@Param("orderno") String orderno,@Param("status") String status);

    Order queryByOrderno(@Param("orderno") String orderno);

    void updateResult(@Param("orderno") String orderno,@Param("status") String status,@Param("data") String data);
}
