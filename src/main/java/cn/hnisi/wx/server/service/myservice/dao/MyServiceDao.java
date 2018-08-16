package cn.hnisi.wx.server.service.myservice.dao;

import cn.hnisi.wx.server.service.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyServiceDao {
    /**
     * 查询我的所有事项
     * @param userid
     * @return
     */
    List<Order> searchServices(@Param("userid") String userid ,@Param("status") String status);

    /**
     * 根据orderno查询事项
     * @param orderno
     * @return
     */
    Order queryMyServiceByOrderNo(@Param("orderno") String orderno);
}
