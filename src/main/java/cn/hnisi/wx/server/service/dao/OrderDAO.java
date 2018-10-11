package cn.hnisi.wx.server.service.dao;

import cn.hnisi.wx.server.service.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDAO {

    void insert(Order order);

    void updateStatus(@Param("orderNo") String orderNo,@Param("status") String status);

    Order queryByOrderNo(@Param("orderNo") String orderNo);

    void update(@Param("orderNo") String orderNo,@Param("order") Order order);
}
