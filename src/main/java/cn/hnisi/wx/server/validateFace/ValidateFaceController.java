package cn.hnisi.wx.server.validateFace;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.utils.RequestBodyParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class ValidateFaceController {

    @Resource
    private ValidateFaceService validateFaceService;

    @PostMapping("/api/frontend/validateFace/signature")
    public ResponseEntity<String> signature(@RequestBodyParam String apiName){
        if(StringUtils.isEmpty(apiName)){
            apiName = "appauth";
        }
        return new ResponseEntity<>(validateFaceService.signature(apiName));
    }

    @PostMapping("/api/frontend/validateFace/saveToken")
    public ResponseEntity<Long> saveToken(@RequestBodyParam String token){
        if(StringUtils.isEmpty(token)){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"token is null");
        }

        Date expiredDate = validateFaceService.saveToken(token);

        return new ResponseEntity<>(expiredDate.getTime());
    }
}
