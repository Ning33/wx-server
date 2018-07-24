package cn.hnisi.wx.server.wxapi;

import cn.hnisi.wx.server.exception.AppException;
import cn.hnisi.wx.server.properties.ValidateFaceProperties;
import cn.hnisi.wx.server.properties.WxProperties;
import cn.hnisi.wx.server.utils.JsonUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@EnableConfigurationProperties(ValidateFaceProperties.class)
public class WxServiceImpl implements IWxService {

    @Resource
    private WxProperties wxProperties;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ValidateFaceProperties validateFaceProperties;

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
        String secret = validateFaceProperties.getSecretKey();
        String appid = validateFaceProperties.getAppid();
        String now = String.valueOf(new Date().getTime()/1000);
        int signExpired = validateFaceProperties.getExpired();

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
        return Base64.getEncoder().encodeToString(mixBytes);
    }

    @Override
    public GetDetectInfoResponse getDetectInfo(String token) {
        String url = "https://iauth-sandbox.wecity.qq.com/new/cgi-bin/getdetectinfo.php";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("signature",this.signature("getdetectinfo"));

        Map<String,Object> inMap = new HashMap<>();
        inMap.put("token",token);
        inMap.put("appid",validateFaceProperties.getAppid());
        inMap.put("crypt_type",3);
        inMap.put("info_type",1);

        HttpEntity<Map> request = new HttpEntity<>(inMap,headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,request,String.class);
        String jsonStr = response.getBody().toString();
        Map dataMap = JsonUtil.convertJsonToBean(jsonStr,Map.class);

        int errorcode = (int) dataMap.get("errorcode");
        String errormsg = (String) dataMap.get("errormsg");
        String data = (String) dataMap.get("data");

        //解密
        try{
            String secret = validateFaceProperties.getResultKey();
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "AES");
            byte[] bytes = Base64.getDecoder().decode(data);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,signingKey);
            byte[] result = cipher.doFinal(bytes);
            String json = new String(result);
            return JsonUtil.convertJsonToBean(json,GetDetectInfoResponse.class);
        }catch(Exception e){
            throw new AppException(e);
        }

    }
}
