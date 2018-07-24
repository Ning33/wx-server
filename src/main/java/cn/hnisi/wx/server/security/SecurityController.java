package cn.hnisi.wx.server.security;


import cn.hnisi.wx.server.exception.AppException;
import cn.hnisi.wx.server.io.ResponseEntity;
import cn.hnisi.wx.server.io.ResponseStatus;
import cn.hnisi.wx.server.properties.WxProperties;
import cn.hnisi.wx.server.wxapi.IWxService;
import org.springframework.beans.BeanUtils;
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
        //session_key不允许传回前端
        User result = new User();
        BeanUtils.copyProperties(user,result,"session_key");
        return new ResponseEntity<>(result);
    }

    @RequestMapping("/api/frontend/signature")
    public ResponseEntity<String> signature(String apiName){
        if(StringUtils.isEmpty(apiName)){
            apiName = "appauth";
        }
        return new ResponseEntity<>(wxService.signature(apiName));
    }

    @RequestMapping("/api/frontend/validateFace/saveToken")
    public ResponseEntity saveToken(String token){
        if(StringUtils.isEmpty(token)){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"token is null");
        }
        //解析token
        IWxService.GetDetectInfoResponse response = wxService.getDetectInfo(token);

        //保存至redis中

        //保存至数据库中


        return new ResponseEntity(ResponseStatus.OK);
    }


    /**
     * 绑定参保人,第一个绑定的是自己
     */
    @RequestMapping("/api/frontend/user/bind")
    public ResponseEntity<User> bind(String name,String idcard){
        //TODO
        return new ResponseEntity<>(ResponseStatus.OK);
    }

    @RequestMapping("/api/frontend/user/unbind")
    public ResponseEntity<Boolean> unbind(String idcard){
        //TODO
        return new ResponseEntity<>(true);
    }


}
