package cn.hnisi.wx.server;

import cn.hnisi.wx.core.CoreAutoConfiguration;
import cn.hnisi.wx.server.properties.WxProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Import(CoreAutoConfiguration.class)
@EnableConfigurationProperties(WxProperties.class)
@EnableTransactionManagement
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
