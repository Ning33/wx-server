package cn.hnisi.wx.server.service.yldyhd.impl;

import cn.hnisi.wx.server.person.PersonService;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.ServiceUtil;
import cn.hnisi.wx.server.service.model.Order;
import cn.hnisi.wx.server.service.model.OrderStatus;
import cn.hnisi.wx.server.service.yldyhd.YldyhdService;
import cn.hnisi.wx.server.service.yldyhd.model.Cbqkqr;
import cn.hnisi.wx.server.service.yldyhd.model.Ffzhqr;
import cn.hnisi.wx.server.service.yldyhd.model.Sbxx;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class YldyhdServiceImpl implements YldyhdService {

    private String serviceName = "yldyhd";

    @Resource
    private ServiceUtil serviceUtil;

    @Resource
    private PersonService personService;

    @Override
    public boolean checkIn(String personid) {
        return false;
    }

    @Override
    public Sbxx querySbxx(String personid) {
        return null;
    }

    @Override
    public Cbqkqr queryCbqkqr(String personid) {
        return null;
    }

    @Override
    public Ffzhqr queryFfzhqr(String personid) {
        return null;
    }

    @Override
    public String submit(String personid, Sbxx sbxx, User user) {
        //查询人员信息
        Person person = personService.queryByPersonid(personid);
        Order order = serviceUtil.createOrder(this.serviceName,user,person,sbxx, OrderStatus.REVIEW);
        return order.getOrderNo();
    }
}
