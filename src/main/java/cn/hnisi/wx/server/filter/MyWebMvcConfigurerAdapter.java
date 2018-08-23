package cn.hnisi.wx.server.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class MyWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    //首先把拦截器进行注册
    @Bean
    public MyIntercepter getMyIntercepter(){
        return new MyIntercepter();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(getMyIntercepter()).addPathPatterns("/api/frontend/service/**");
        super.addInterceptors(registry);
    }
}
