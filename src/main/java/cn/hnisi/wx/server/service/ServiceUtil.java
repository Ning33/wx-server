package cn.hnisi.wx.server.service;

import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.dao.OrderDAO;
import cn.hnisi.wx.server.service.model.Order;
import cn.hnisi.wx.server.service.model.OrderStatus;
import cn.hnisi.wx.server.ywserver.model.ResponseDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceUtil {

    @Resource
    private OrderNoGenerator orderNoGenerator;

    @Resource
    private OrderDAO orderDAO;

    public String generateOrderNo(){
        return orderNoGenerator.generate();
    }

    public <T> Order createOrder(String serviceName,User user, Person person, T data){
        return createOrder(serviceName,user,person,data,OrderStatus.SUCCESS);
    }

    public <T> Order createOrder(String serviceName,User user, Person person, T data,OrderStatus orderStatus){
        Order order = new Order();
        //生成受理单号
        order.setOrderNo(orderNoGenerator.generate());
        //插入用户信息
        order.setUserId(user.getUserId());
        order.setUserIdcard(user.getIdcard());
        order.setUserName(user.getName());
        //插入人员信息
        order.setPersonId(person.getPersonId());
        order.setPersonIdcard(person.getIdcard());
        order.setPersonName(person.getName());
        //插入业务数据
        if(data == null){
            order.setRequestData("");
        }else{
            order.setRequestData(JsonUtil.convertBeanToJson(data));
        }

        //插入业务状态
        order.setStatus(orderStatus.toString());
        //插入事项类型
        order.setServiceName(serviceName);
        //存入数据库
        orderDAO.insert(order);

        return order;
    }

    public Order query(String orderNo){
        return orderDAO.queryByOrderNo(orderNo);
    }

    public <T> void callback(ResponseDTO responseDTO){
        Order order = new Order();
        String orderNo = responseDTO.getOrderNo();

        if(responseDTO.isSuccess()){
            // 回写状态
            order.setStatus(OrderStatus.SUCCESS.toString());
            // 回写业务流水号
            order.setBae007(responseDTO.getBae007());
            // 回写responseData
            order.setResponseData(JsonUtil.convertBeanToJson(responseDTO.getResponseData()));
            // 回写requestData,可选
        }else{
            // 回写状态
            order.setStatus(OrderStatus.FAIL.toString());
            // 回写业务流水号
            order.setBae007(responseDTO.getBae007());
            // 回写requestData,可选
        }

        orderDAO.update(orderNo,order);
    }
}
