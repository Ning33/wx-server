package cn.hnisi.wx.server;

import cn.hnisi.wx.core.CoreAutoConfiguration;
import cn.hnisi.wx.server.properties.WxProperties;
import cn.hnisi.wx.server.validateface.ValidateFaceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Import(CoreAutoConfiguration.class)
@EnableConfigurationProperties(WxProperties.class)
@EnableTransactionManagement
@EnableScheduling
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ValidateFaceService validateFaceService){
        return args -> {
            System.out.println("server started");
//            validateFaceService.saveToken("{91378D24-D710-4C87-B2DF-D23C9FC53A97}","{\"name\":\"张三\",\"phone\":\"\",\"sex\":\"\",\"nation\":\"\",\"validatedata\":\"\",\"frontpic\":null,\"backpic\":null,\"videopic1\":null,\"videopic2\":null,\"videopic3\":null,\"video\":null,\"yt_errorcode\":0,\"yt_errormsg\":\"成功\",\"livestatus\":0,\"livemsg\":\"成功\",\"comparestatus\":0,\"comparemsg\":\"成功\",\"type\":0,\"id\":\"420116199011214537\",\"id_address\":null,\"id_valid_date\":null,\"id_authority\":null,\"id_birth\":null,\"ID\":\"440123198712023652\"}");
        };
    }
}
