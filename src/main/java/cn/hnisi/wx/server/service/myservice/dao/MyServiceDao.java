package cn.hnisi.wx.server.service.myservice.dao;

import cn.hnisi.wx.server.service.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyServiceDao {
    /**
     * 查询我的所有事项
     * @param userId
     * @return
     */
    List<Order> searchServices(@Param("userId") String userId ,@Param("status") String status);

    /**
     * 根据orderNo查询事项
     * @param orderNo
     * @return
     */
    Order queryMyServiceByOrderNo(@Param("orderNo") String orderNo);
}
