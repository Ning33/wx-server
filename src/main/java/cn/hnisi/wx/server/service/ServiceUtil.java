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

    public Order createOrder(String serviceName,User user, Person person, Object data){
        Order order = new Order();
        //生成受理单号
        order.setOrderno(orderNoGenerator.generate());
        //插入用户信息
        order.setUserid(user.getUserid());
        order.setUserIdcard(user.getIdcard());
        order.setUserName(user.getName());
        //插入人员信息
        order.setPersonid(person.getPersonid());
        order.setPersonIdcard(person.getIdcard());
        order.setPersonName(person.getName());
        //插入业务数据
        if(data == null){
            order.setData("");
        }else{
            order.setData(JsonUtil.convertBeanToJson(data));
        }

        //插入业务状态
        order.setStatus(OrderStatus.APPLY.toString());
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
        return orderDAO.queryByOrderno(orderno);
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
