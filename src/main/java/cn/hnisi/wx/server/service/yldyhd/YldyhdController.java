package cn.hnisi.wx.server.service.yldyhd;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.ServiceUtil;
import cn.hnisi.wx.server.service.model.Order;
import cn.hnisi.wx.server.service.yldyhd.model.CallbackRequest;
import cn.hnisi.wx.server.service.yldyhd.model.CbqkqrResponse;
import cn.hnisi.wx.server.service.yldyhd.model.FfzhqrResponse;
import cn.hnisi.wx.server.service.yldyhd.model.SbxxResponse;
import cn.hnisi.wx.server.validateface.ValidateFaceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
public class YldyhdController {

    @Resource
    private YldyhdService yldyhdService;

    @Resource
    private ValidateFaceService validateFaceService;

    @Resource
    private ServiceUtil serviceUtil;

    /**
     * 检查是否存在未办结的业务
     * @param user
     * @return
     */
    @RequestMapping("/api/frontend/service/yldyhd/pre-check")
    public ResponseEntity preCheck(User user){
        // 查询是否存在未办结业务


        return new ResponseEntity();
    }

    /**
     * 检查是否允许办理
     * 若允许办理则查询申报信息返回
     * @return
     */
    @RequestMapping("/api/frontend/service/yldyhd/check-in")
    public ResponseEntity<SbxxResponse> checkIn(String personid, User user, HttpServletRequest request){
        SbxxResponse response = new SbxxResponse();
        response.setXm("测试人员001");
        return new ResponseEntity<>(response);
    }

    /**
     * 提交申报资料，开启流程
     * @return
     */
    @RequestMapping("/api/frontend/service/yldyhd/step-sbxx-submit")
    public ResponseEntity<String> submitSbxx(String personid,User user,HttpServletRequest request){
        String orderno = yldyhdService.submitSbxx(personid,user);
        return new ResponseEntity<>(orderno);
    }

    /**
     * 查询参保情况确认信息
     * @return
     */
    @RequestMapping("/api/frontend/service/yldyhd/step-cbqkqr-query")
    public ResponseEntity<CbqkqrResponse> queryCbqkqr(String orderno,User user,HttpServletRequest request){
        CbqkqrResponse response = new CbqkqrResponse();
        response.setStjfys(80);
        response.setSjjfys(100);
        response.setLjjfys(180);
        response.setStzhje(new BigDecimal(80040.23));
        response.setYlzhje(new BigDecimal(99932.45));

        return new ResponseEntity<>(response);
    }

    /**
     * 查询发放账户确认信息
     * @return
     */
    @RequestMapping("/api/frontend/service/yldyhd/step-ffzhqr-query")
    public ResponseEntity<FfzhqrResponse> yhxxqr(String orderno, User user, HttpServletRequest request){
        FfzhqrResponse response = new FfzhqrResponse();
        response.setXm("张三");
        response.setZjhm("666666666");
        response.setYhkzh("888888888888");
        response.setYlsjhm("15145465899");
        return new ResponseEntity<>(response);
    }

    /**
     * 提交业务申报
     * @return
     */
    @RequestMapping("/api/frontend/service/yldyhd/submit")
    public ResponseEntity submit(String orderno,User user,HttpServletRequest request){
        //从订单中获取参保人信息
        Order order = serviceUtil.query(orderno);
        //验证人脸
        validateFaceService.validateToken(request,order.getPersonIdcard());

        yldyhdService.submit(orderno);
        return new ResponseEntity();
    }

    /**
     * 回写业务办理结果
     * @return
     */
    @RequestMapping("/api/server/service/yldyhd/callback")
    public ResponseEntity callback(CallbackRequest request){
        return null;
    }
}
