package cn.hnisi.wx.server.security;


import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.validateface.ValidateFaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SecurityController {

    @Resource
    private SecurityService securityService;

    @Resource
    private ValidateFaceService validateFaceService;

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
     * 注册
     */
    @RequestMapping("/api/frontend/user/register")
    public ResponseEntity<User> register(String idcard, String name, User user,HttpServletRequest request){
        //验证人脸和注册用户是否一致
        validateFaceService.validateToken(request,idcard,name);

        //注册用户信息
        user.setIdcard(idcard);
        user.setName(name);
        User resultUser = securityService.register(user);

        return new ResponseEntity<>(resultUser);
    }

    /**
     * TODO
     * 注销用户，暂不实现
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/api/frontend/user/unregister")
    public ResponseEntity unregister(User user,HttpServletRequest request){
        //验证人脸和注册用户是否一致
        validateFaceService.validateToken(request,user.getIdcard(),user.getName());

        return new ResponseEntity(ResponseStatus.UNKNOWN_ERROR,"此功能暂未开放");
    }

}
