package cn.hnisi.wx.server.service.yldyhd.impl;

import cn.hnisi.wx.server.person.PersonService;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.ServiceUtil;
import cn.hnisi.wx.server.service.model.Order;
import cn.hnisi.wx.server.service.yldyhd.YldyhdService;
import cn.hnisi.wx.server.service.yldyhd.model.CbqkqrResponse;
import cn.hnisi.wx.server.service.yldyhd.model.FfzhqrResponse;
import cn.hnisi.wx.server.service.yldyhd.model.SbxxResponse;
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
    public SbxxResponse querySbxx(String personid) {
        return null;
    }

    @Override
    public String submitSbxx(String personid,User user) {
        //查询人员信息
        Person person = personService.queryByPersonid(personid);
        Order order = serviceUtil.createOrder(this.serviceName,user,person,null);
        return order.getOrderno();
    }

    @Override
    public CbqkqrResponse queryCbqkqr(String orderno) {
        return null;
    }

    @Override
    public FfzhqrResponse queryFfzhqr(String orderno) {
        return null;
    }

    @Override
    public void submit(String orderno) {
        serviceUtil.submit(orderno);
    }
}
