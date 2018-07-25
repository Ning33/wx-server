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
        AppException exception = (AppException) request.getAttribute("exception");
        ResponseStatus status = exception.getStatus();
        return new ResponseEntity(status,exception.getErrmsg());
    }
}
