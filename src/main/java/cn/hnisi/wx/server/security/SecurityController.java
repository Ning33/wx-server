package cn.hnisi.wx.server.security;


import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.security.ISecurityService;
import cn.hnisi.wx.core.security.User;
import cn.hnisi.wx.core.wxapi.IWxService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
public class SecurityController {

    @Resource
    private IWxService wxService;

    @Resource
    private ISecurityService securityService;

    @RequestMapping("/api/frontend/user/login")
    public ResponseEntity<User> login(@NotNull String jsCode){
        if(true){
            return new ResponseEntity<>(ResponseStatus.JS_CODE_INVALID);
        }
        //调用微信接口，换取微信身份信息
        User user = wxService.jscode2session(jsCode);
        //调用业务接口，注入个人基本信息
        securityService.injectUserPersonalInfo(user);
        //存储用户信息至用户中心，目前使用redis管理
        securityService.storeUser(user);
        return new ResponseEntity<>(user);
    }

    @RequestMapping("/api/frontend/user/bind")
    public ResponseEntity<User> bind(){
        //TODO
        return null;
    }

    @RequestMapping("/api/frontend/user/unbind")
    public ResponseEntity<Boolean> unbind(){
        //TODO
        return new ResponseEntity<>(true);
    }


}
