package cn.hnisi.wx.server.filter;

import cn.hnisi.wx.core.utils.RequestBodyParam;
import cn.hnisi.wx.core.utils.JsonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 处理方法中接收的参数
 */
@Configuration
public class HandlerParameterResolver implements HandlerMethodArgumentResolver {
    /**
     * 此方法拦截加了注解的参数 方法
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //拦截添加注解的方法参数
        if(methodParameter.hasParameterAnnotation(RequestBodyParam.class)){
            return true;
        }
        return false;
    }

    /**
     * 对加了自定义注解的方法进行业务操作
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //获取注解的属性值
        String value = methodParameter.getParameterAnnotation(RequestBodyParam.class).value();
        if(StringUtils.isEmpty(value)){
            //如注解没有定义属性值, 则获取参数名称
            value = methodParameter.getParameterName();
        }
        //获取request
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        //获取request里面json格式数据,转换成map
        Map<String,Object> json = JsonUtil.getRequestJsonObject(request);

        return json.get(value);
    }
}
