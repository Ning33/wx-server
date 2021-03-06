package cn.hnisi.wx.server.filter;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.server.person.dao.PersonDAO;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.service.dao.OrderDAO;
import cn.hnisi.wx.server.service.model.Order;
import cn.hnisi.wx.server.service.navigation.dao.ServiceItemDAO;
import cn.hnisi.wx.server.validateFace.ValidateFaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截器
 */
public class MyIntercepter implements HandlerInterceptor{

    private static final Logger log = LoggerFactory.getLogger(MyIntercepter.class);

    @Resource
    private ServiceItemDAO serviceItemDAO;

    @Resource
    private PersonDAO personDAO;
    @Resource
    private OrderDAO orderDAO;
    @Resource
    private ValidateFaceService validateFaceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-----------------开始进入请求地址拦截-----------------");
        //获取post请求中的参数
        Map<Object,String> json = JsonUtil.getRequestJsonObject(request);
        String serviceName; //服务名
        String personId;    //参保人id
        Order order ;        //订单
        Person person = null;      //参保人
        try{
            //截取url路径,获取服务业务名 service_name
            int preNum = request.getRequestURL().indexOf("service");
            int subNum = request.getRequestURL().indexOf("/",preNum+8);
            serviceName = request.getRequestURL().substring(preNum+8,subNum);
            //查询安全级别 或 判断有无此事项
            String securityLevel = serviceItemDAO.queryByServiceName(serviceName);
            if(!StringUtils.isEmpty(securityLevel) && securityLevel.equals("3")){ //需要人脸识别的事项
                //判断用户是否已经人脸识别
                personId = json.get("personId");

                //如果参数中没有personId 则试用orderNo查询personId
                if(StringUtils.isEmpty(personId)){
                    String orderNo = json.get("orderNo");
                    //如果orderNo也没有获取到, 则抛出异常
                    if(StringUtils.isEmpty(orderNo)){
                        throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"数据为空");
                    }
                    order = orderDAO.queryByOrderNo(orderNo);
                    if(order == null){
                        throw new AppException(ResponseStatus.UNKNOWN_ERROR);
                    }
                    personId = order.getPersonId();
                }
                //查询参保人获取其身份号码
                person = personDAO.queryByPersonId(personId);
                if(person == null){
                    throw new AppException(ResponseStatus.UNKNOWN_ERROR);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"数据解析异常");
        }
        //检验token
        validateFaceService.validateToken(request,person.getIdcard(),person.getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
