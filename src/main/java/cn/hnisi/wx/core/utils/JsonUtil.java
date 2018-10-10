package cn.hnisi.wx.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static{
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    public static class UpperCaseStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase{
        @Override
        public String translate(String input) {
            if (input == null || input.length() == 0){
                return input; // garbage in, garbage out
            }

            return input.toUpperCase();
        }
    }


    public static <T> T convertJsonToBean(String json,Class<T> cls){
        try {
            return objectMapper.readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertJsonToBean(byte[] bytes,Class<T> cls){
        try{
            return objectMapper.readValue(bytes,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String convertBeanToJson(T t){
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertBeanToBean(Object obj,Class<T> cls){
        return convertJsonToBean(convertBeanToJson(obj),cls);
    }


    /**
     * 获取request 请求中的参数
     * @param request
     * @return
     */
    public static Map getRequestJsonObject(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        //判断请求方式
        if(request.getMethod().equals(HttpMethod.GET.toString())){
            String personId =  request.getParameter("personId");
            if(StringUtils.isEmpty(personId)){
                String orderNo = request.getParameter("orderNo");
                map.put("orderNo",orderNo);
            }else{
                map.put("personId" , personId);
            }
            return map;
        }else{
            RequestWrapper requestWrapper = new RequestWrapper(request);
            String json = requestWrapper.getBody();
            map =  convertJsonToBean(json , Map.class);
            return map;

        }

    }

}
