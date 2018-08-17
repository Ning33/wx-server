package cn.hnisi.wx.server.filter;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.server.person.dao.PersonDAO;
import cn.hnisi.wx.server.person.model.Person;
import cn.hnisi.wx.server.service.service_navigation.dao.ServiceItemDAO;
import cn.hnisi.wx.server.service.service_navigation.impl.NavigationServiceImpl;
import cn.hnisi.wx.server.validateface.ValidateFaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class MyIntercepter implements HandlerInterceptor{

    private static final Logger log = LoggerFactory.getLogger(MyIntercepter.class);

    @Resource
    private ServiceItemDAO serviceItemDAO;

    @Resource
    private PersonDAO personDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-----------------开始进入请求地址拦截-----------------");
        //截取url路径,获取服务业务名 service_name
        int preNum = request.getRequestURL().indexOf("service");
        int subNum = request.getRequestURL().indexOf("/",preNum+8);
        String serviceName = request.getRequestURL().substring(preNum+8,subNum);
        //查询安全级别 或 判断有无此事项
        String securityLevel = serviceItemDAO.queryByServiceName(serviceName);
        if(!StringUtils.isEmpty(securityLevel) && securityLevel.equals("3")){ //需要人脸识别的事项
            //判断用户是否已经人脸识别
            String personid =  request.getParameter("personid");
            Person person = personDAO.queryByPersonid(personid);
            System.out.println(person.getIdcard());
            if(person == null){
                throw new AppException(ResponseStatus.UNKNOWN_ERROR);
            }

            //检验token
            ValidateFaceService validateFaceService = new ValidateFaceService();
            validateFaceService.validateToken(request,person.getIdcard());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
