package cn.hnisi.wx.server.security;


import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.io.ResponseStatus;
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

    @RequestMapping("/frontend/user/checkLogin")
    public ResponseEntity<User> checkLogin(String sessionid){
        if(StringUtils.isEmpty(sessionid)){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION);
        }
        User user = securityService.getUser(sessionid);
        return new ResponseEntity<>(user);
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
