package cn.hnisi.wx.server.service.grsfjy;

import cn.hnisi.wx.core.exception.DataValidationException;
import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.server.person.PersonService;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.ServiceUtil;
import cn.hnisi.wx.server.service.model.Order;
import cn.hnisi.wx.server.service.model.OrderStatus;
import cn.hnisi.wx.server.service.grsfjy.model.Sbxx;
import cn.hnisi.wx.server.ywserver.YwServerProxy;
import cn.hnisi.wx.server.ywserver.model.AttachFile;
import cn.hnisi.wx.server.ywserver.model.ExchangeData;
import cn.hnisi.wx.server.ywserver.model.RequestDTO;
import cn.hnisi.wx.server.ywserver.model.ResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class GrsfjyService {

    private final String serviceName = "wxGrsfjy";

    @Resource
    private ServiceUtil serviceUtil;

    @Resource
    private PersonService personService;

    @Resource
    private YwServerProxy ywServerProxy;

    /**
     * 业务准入校验
     * @param personId
     * @return
     */
    public Sbxx checkIn(String personId){
        Person person = personService.queryByPersonId(personId);

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setIdcard(person.getIdcard());
        requestDTO.setName(person.getName());

        ResponseDTO responseDTO = ywServerProxy.request(serviceName,"checkIn",requestDTO);
        if(responseDTO.isSuccess()){
            Object sbxx = responseDTO.getResponseData().getGtData().get("sbxx");
            return JsonUtil.convertBeanToBean(sbxx,Sbxx.class);
        }else{
            throw new DataValidationException(responseDTO.getRemark());
        }
    }

    /**
     * 提交业务申报
     * @param personId
     * @return
     */
    @Transactional
    String submit(String personId, AttachFile attachFile, User user){
        //查询人员信息
        Person person = personService.queryByPersonId(personId);

        //调用业务接口进行业务申报
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setIdcard(person.getIdcard());
        requestDTO.setName(person.getName());


        //构造业务请求数据
        ExchangeData requestData = new ExchangeData();
        Map<String,Object> gtData = new HashMap<>();
        gtData.put("attachFile",attachFile);
        requestData.setGtData(gtData);
        requestDTO.setRequestData(requestData);

        //生成受理单并保存至数据库
        Order order = serviceUtil.createOrder(serviceName,user,person,requestData, OrderStatus.REVIEW);
        String orderNo = order.getOrderNo();
        requestDTO.setOrderNo(orderNo);

        //发送核心系统请求
        ResponseDTO responseDTO = ywServerProxy.request(serviceName,"submit",requestDTO);
        responseDTO.setOrderNo(orderNo);
        if(responseDTO.isSuccess()){
            // 返回受理单号
            serviceUtil.callback(responseDTO);
            return orderNo;
        }else{
            // 抛出异常，回滚受理单
            throw new DataValidationException(responseDTO.getRemark());
        }
    }
}
