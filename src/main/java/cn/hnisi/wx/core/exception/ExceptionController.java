package cn.hnisi.wx.core.exception;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.io.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExceptionController {
    @RequestMapping("/exception")
    public ResponseEntity handleException(HttpServletRequest request){
        Exception exception = (Exception) request.getAttribute("exception");
        if(exception instanceof  AppException){
            AppException appException = (AppException) exception;
            ResponseStatus status = appException.getStatus();
            return new ResponseEntity(status,appException.getErrmsg());
        }else{
            exception.printStackTrace();
            ResponseStatus status = ResponseStatus.UNKNOWN_ERROR;
            return new ResponseEntity(status,exception.getMessage());
        }

    }
}
