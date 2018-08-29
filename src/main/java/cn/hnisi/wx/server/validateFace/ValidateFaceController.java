package cn.hnisi.wx.server.validateFace;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.io.ResponseStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class ValidateFaceController {

    @Resource
    private ValidateFaceService validateFaceService;

    @RequestMapping("/api/frontend/validateFace/signature")
    public ResponseEntity<String> signature(String apiName){
        if(StringUtils.isEmpty(apiName)){
            apiName = "appauth";
        }
        return new ResponseEntity<>(validateFaceService.signature(apiName));
    }

    @RequestMapping("/api/frontend/validateFace/saveToken")
    public ResponseEntity<Long> saveToken(String token){
        if(StringUtils.isEmpty(token)){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"token is null");
        }

        Date expiredDate = validateFaceService.saveToken(token);

        return new ResponseEntity<>(expiredDate.getTime());
    }
}
