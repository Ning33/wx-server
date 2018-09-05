package cn.hnisi.wx.server.filter;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.core.utils.SecurityLevel;
import cn.hnisi.wx.server.filter.dao.ApiDAO;
import cn.hnisi.wx.server.filter.model.Api;
import cn.hnisi.wx.server.person.dao.PersonDAO;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.security.SecurityService;
import cn.hnisi.wx.server.security.model.User;
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

    private static final String sessionHeaderKey = "x-tif-sessionid";
    private final String serviceUrl = "/service/";

    @Resource
    private SecurityService securityService;
    @Resource
    private ServiceItemDAO serviceItemDAO;
    @Resource
    private PersonDAO personDAO;
    @Resource
    private OrderDAO orderDAO;
    @Resource
    private ValidateFaceService validateFaceService;
    @Resource
    private ApiDAO apiDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-----------------开始进入请求地址拦截-----------------");

        //获取url
        String url = request.getRequestURI();
        //获取请求中的参数
        Map<String,Object> json = JsonUtil.getRequestJsonObject(request);
        //获取api信息
        Api api  = apiDAO.querySecurityByUrl(url);
        if(api != null){
            //判断安全等级--需实名
            if(api.getSecurityLevel().equals(SecurityLevel.REAL_NAME.getStatus())){
                checkBindUser(request);
            }
            //判断安全等级--需人脸验证
            if(api.getSecurityLevel().equals(SecurityLevel.VALIDATE_FACE.getStatus())){
                Person person = checkPerson(json);
                //检验token
                validateFaceService.validateToken(request,person.getIdcard(),person.getName());
            }

            return true;
        }else{

            //截取url路径,获取服务业务名 service_name
            int preNum = request.getRequestURL().indexOf(serviceUrl);
            //url中不属于服务项,直接过
            if(preNum == -1){
                return true;
            }
            int subNum = request.getRequestURL().indexOf("/",preNum+serviceUrl.length());
            //服务事项名
            String serviceName = request.getRequestURL().substring(preNum+serviceUrl.length(),subNum);

            //查询安全级别 或 判断有无此事项
            String securityLevel = serviceItemDAO.queryByServiceName(serviceName);
            //需要实名验证的事项
            if(!StringUtils.isEmpty(securityLevel) && securityLevel.equals(SecurityLevel.REAL_NAME.getStatus())){
                checkBindUser(request);
            }
            //需要人脸识别的事项
            if(!StringUtils.isEmpty(securityLevel) && securityLevel.equals(SecurityLevel.VALIDATE_FACE.getStatus())){ //需要人脸识别的事项
                Person person = checkPerson(json);
                //检验token
                validateFaceService.validateToken(request,person.getIdcard(),person.getName());
            }

            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    /**
     * 根据personid 或 orderID查询参保人
     * @param json
     * @return
     */
    private Person checkPerson(Map<String,Object> json ){
        String personId;    //参保人id
        Order order ;        //订单
        Person person = null;      //参保人
        //判断用户是否已经人脸识别
        personId = (String)json.get("personId");

        //如果参数中没有personId 则试用orderNo查询personId
        if(StringUtils.isEmpty(personId)){
            String orderNo = (String)json.get("orderNo");
            //如果orderNo也没有获取到, 则抛出异常
            if(StringUtils.isEmpty(orderNo)){
                throw new AppException(ResponseStatus.UNBOUND_USER,"请实名");
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
        return person;
    }

    /**
     * 实名验证
     * @param request
     */
    private void checkBindUser(HttpServletRequest request){
        String sessionid = request.getHeader(sessionHeaderKey);
        if(StringUtils.isEmpty(sessionid)){
            throw new AppException(ResponseStatus.UNBOUND_USER,"请实名验证");
        }
        User user = securityService.getUser(sessionid);
        if(user == null){
            throw new AppException(ResponseStatus.UNBOUND_USER,"请实名验证");
        }
        boolean boundIdcard = user.isBoundIdcard();
        //未实名绑定
        if(!boundIdcard){
            throw new AppException(ResponseStatus.UNBOUND_USER,"请实名验证");
        }
    }

}
