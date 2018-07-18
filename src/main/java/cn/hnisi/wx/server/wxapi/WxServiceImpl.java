package cn.hnisi.wx.server.wxapi;

import cn.hnisi.wx.server.WxProperties;
import cn.hnisi.wx.server.exception.AppException;
import cn.hnisi.wx.server.utils.JsonUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
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

    @Override
    public String signature(String apiName) {
        String secret = wxProperties.getSecret();
        String appid = wxProperties.getAppid();
        appid = appid.substring(appid.length()-4);
        String now = String.valueOf(Math.round(new Date().getTime()/1000));
        int signExpired = 600;

        String originStr = "a="+appid+"&m="+apiName+"&t="+now+"&e="+signExpired;

        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA1");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e ) {
            throw new AppException("签名异常",e);
        }

        byte[] originBytes = mac.doFinal(originStr.getBytes());
        byte[] mixBytes = ArrayUtils.addAll(originBytes,originStr.getBytes());
        String encodeStr = new String(Base64.encodeBase64(mixBytes));
        return encodeStr;
    }
}
