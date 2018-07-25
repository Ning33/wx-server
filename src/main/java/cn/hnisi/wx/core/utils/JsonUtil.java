package cn.hnisi.wx.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static{
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
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

}
