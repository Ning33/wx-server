package cn.hnisi.wx.server.ywserver;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.server.ywserver.model.RequestDTO;
import cn.hnisi.wx.server.ywserver.model.ResponseDTO;
import cn.hnisi.wx.server.ywserver.model.YwServerResponseEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class YwServerProxy implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private YwServerProperties ywServerProperties;

    /**
     * 发送核心系统请求
     * @param serviceName
     * @param serviceMethod
     * @param requestDTO
     * @return
     */
    public ResponseDTO request(String serviceName, String serviceMethod, RequestDTO requestDTO){
        boolean isMock = true;
        if(isMock){
            String mockServiceSuffix = "MockService";
            Class cls = applicationContext.getBean(serviceName+mockServiceSuffix).getClass();

            Method method = null;
            try{
                method = cls.getMethod(serviceMethod,RequestDTO.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            Object obj = applicationContext.getBean(serviceName+mockServiceSuffix);

            try {
                return (ResponseDTO) method.invoke(obj,requestDTO);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }


        }else {
            String url = ywServerProperties.getUri() + "/api/server/" + serviceName + "/" + serviceMethod;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

            HttpEntity<RequestDTO> request = new HttpEntity<>(requestDTO, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            String jsonStr = response.getBody();
            YwServerResponseEntity responseEntity = JsonUtil.convertJsonToBean(jsonStr, YwServerResponseEntity.class);
            if(responseEntity.getErrcode().equals(ResponseStatus.OK.getErrcode())){
                return responseEntity.getData();
            }else{
                throw new AppException(responseEntity.getErrmsg());
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
