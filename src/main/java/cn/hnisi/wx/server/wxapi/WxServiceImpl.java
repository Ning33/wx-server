package cn.hnisi.wx.server.wxapi;

import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.server.properties.ValidateFaceProperties;
import cn.hnisi.wx.server.properties.WxProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@EnableConfigurationProperties(ValidateFaceProperties.class)
public class WxServiceImpl implements IWxService {

    @Resource
    private WxProperties wxProperties;

    @Resource
    private RestTemplate restTemplate;


    @Override
    public Jscode2sessionResponse jscode2session(String jsCode) {

        Map<String,Object> inMap = new HashMap<>();
        inMap.put("appid",wxProperties.getAppid());
        inMap.put("secret",wxProperties.getSecret());
        inMap.put("js_code",jsCode);
        inMap.put("grant_type","authorization_code");
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}";

        String jsonResult = restTemplate.getForObject(url,String.class,inMap);
        Jscode2sessionResponse result = JsonUtil.convertJsonToBean(jsonResult,Jscode2sessionResponse.class);
        return result;
    }


}
