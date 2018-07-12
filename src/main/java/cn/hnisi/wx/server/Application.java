package cn.hnisi.wx.server;

import cn.hnisi.wx.core.CoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CoreAutoConfiguration.class)
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
