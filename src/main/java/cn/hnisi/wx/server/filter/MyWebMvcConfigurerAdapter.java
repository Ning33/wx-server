package cn.hnisi.wx.server.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {


    //首先把拦截器进行注册
    @Bean
    public MyIntercepter getMyIntercepter(){
        return new MyIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(getMyIntercepter()).addPathPatterns("/api/frontend/**");
        super.addInterceptors(registry);
    }
}
