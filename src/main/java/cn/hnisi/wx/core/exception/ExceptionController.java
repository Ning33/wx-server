package cn.hnisi.wx.core.exception;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.io.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ExceptionController {
    @RequestMapping("/exception")
    public ResponseEntity handleException(HttpServletRequest request){
        Exception exception = (Exception) request.getAttribute("exception");
        if(exception instanceof  AppException){
            AppException appException = (AppException) exception;
            ResponseStatus status = appException.getStatus();

            //人脸识别过期，需要额外在data中传递人员信息
            if(appException instanceof ValidateFaceException){
                ValidateFaceException validateFaceException = (ValidateFaceException) appException;
                Map<String,String> data = new HashMap<String,String>();
                data.put("idcard",validateFaceException.getIdcard());
                data.put("name",validateFaceException.getName());
                return new ResponseEntity<Map>(data,status,appException.getErrmsg());
            }

            return new ResponseEntity(status,appException.getErrmsg());
        }else{
            exception.printStackTrace();
            ResponseStatus status = ResponseStatus.UNKNOWN_ERROR;
            return new ResponseEntity(status,exception.getMessage());
        }

    }
}
