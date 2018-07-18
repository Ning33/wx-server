package cn.hnisi.wx.server.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convertJsonToBean(String json,Class<T> cls){
        try {
            return objectMapper.readValue(json,cls);
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
