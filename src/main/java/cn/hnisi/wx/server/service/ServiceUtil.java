package cn.hnisi.wx.server.service;

import cn.hnisi.wx.core.exception.DataValidationException;
import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.dao.OrderDAO;
import cn.hnisi.wx.server.service.model.Order;
import cn.hnisi.wx.server.service.model.OrderStatus;
import cn.hnisi.wx.server.service.model.ServiceResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceUtil {

    @Resource
    private OrderNoGenerator orderNoGenerator;

    @Resource
    private OrderDAO orderDAO;

    public <T> Order createOrder(String serviceName,User user, Person person, T data){
        return createOrder(serviceName,user,person,data,OrderStatus.APPLY);
    }

    public <T> Order createOrder(String serviceName,User user, Person person, T data,OrderStatus orderStatus){
        Order order = new Order();
        //生成受理单号
        order.setOrderNo(orderNoGenerator.generate());
        //插入用户信息
        order.setUserId(user.getUserid());
        order.setUserIdcard(user.getIdcard());
        order.setUserName(user.getName());
        //插入人员信息
        order.setPersonId(person.getPersonid());
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

    public void submit(String orderno){
        orderDAO.updateStatus(orderno, OrderStatus.REVIEW.toString());
    }

    public Order query(String orderno){
        return orderDAO.queryByOrderNo(orderno);
    }

    public <T> void update(ServiceResult<T> serviceResult){
        String orderno = serviceResult.getOrderno();
        String status = serviceResult.getStatus();
        // 校验status
        if(!(status.equals("21")||status.equals("22"))){
            throw new DataValidationException("status 数据不符合规范");
        }
        String data = JsonUtil.convertBeanToJson(serviceResult.getData());
        orderDAO.updateResult(orderno,status,data);
    }
}
