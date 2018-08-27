package cn.hnisi.wx.server.security;

import cn.hnisi.wx.server.security.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

@Component
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String sessionHeaderKey = "x-tif-sessionid";

    @Resource
    private SecurityService securityService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if(parameter.getParameterType().equals(User.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String sessionid = webRequest.getHeader(sessionHeaderKey);
        if(StringUtils.isEmpty(sessionid)){
            return null;
        }
        return securityService.getUser(sessionid);
    }
}
