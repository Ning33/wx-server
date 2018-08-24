package cn.hnisi.wx.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    /**
     * 获取request 请求中的参数
     * @param request
     * @return
     * @throws IOException
     */
    public static Map getRequestJsonObject(HttpServletRequest request) throws IOException {
        String json = getRequestJsonString(request);
        Map map =  convertJsonToBean(json , Map.class);
        return map;
    }
    /***
     * 获取 request 中 json 字符串的内容
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");
            // POST
        } else {
            return getRequestPostStr(request);
        }
    }

    /**
     * 获取 post 请求的 byte[] 数组
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 获取 post 请求内容
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

}
