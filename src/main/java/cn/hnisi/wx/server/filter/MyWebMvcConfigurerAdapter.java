package cn.hnisi.wx.server.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
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

    /**
     * 访问静态资源
     * addResourceHandler: 访问的路径
     * addResourceLocations: 静态资源的位置
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/img");
        super.addResourceHandlers(registry);
    }
}
