package cn.hnisi.wx.server.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class AutoConfiguration implements WebMvcConfigurer {

    @Resource
    private UserHandlerMethodArgumentResolver userHandlerMethodArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(userHandlerMethodArgumentResolver);
    }
}
