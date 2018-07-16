package cn.hnisi.wx.server.security;


import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.security.ISecurityService;
import cn.hnisi.wx.core.security.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
public class SecurityController {

    @Resource
    private ISecurityService securityService;

    @RequestMapping("/api/frontend/user/login")
    public ResponseEntity<User> login(@NotNull String jsCode){
        if(StringUtils.isEmpty(jsCode)){
            throw new AppException(ResponseStatus.JS_CODE_INVALID,"jsCode is null");
        }

        User user = securityService.login(jsCode);

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
