package cn.hnisi.wx.server.security;


import cn.hnisi.wx.server.WxProperties;
import cn.hnisi.wx.server.exception.AppException;
import cn.hnisi.wx.server.io.ResponseEntity;
import cn.hnisi.wx.server.io.ResponseStatus;
import cn.hnisi.wx.server.wxapi.IWxService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SecurityController {

    @Resource
    private ISecurityService securityService;

    @Resource
    private WxProperties wxProperties;

    @Resource
    private IWxService wxService;

    @RequestMapping("/api/frontend/user/login")
    public ResponseEntity<User> login(String jsCode){
        if(StringUtils.isEmpty(jsCode)){
            throw new AppException(ResponseStatus.JS_CODE_INVALID,"jsCode is null");
        }

        User user = securityService.login(jsCode);

        return new ResponseEntity<>(user);
    }

    @RequestMapping("/api/frontend/signature")
    public ResponseEntity<String> signature(String apiName){
        if(StringUtils.isEmpty(apiName)){
            apiName = "appauth";
        }
        return new ResponseEntity<>(wxService.signature(apiName));
    }

    @RequestMapping("/api/frontend/user/bind")
    public ResponseEntity<User> bind(){
        //TODO
        return new ResponseEntity<>(ResponseStatus.OK);
    }

    @RequestMapping("/api/frontend/user/unbind")
    public ResponseEntity<Boolean> unbind(){
        //TODO
        return new ResponseEntity<>(true);
    }


}
