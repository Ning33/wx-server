package cn.hnisi.wx.server.service.yldyhd;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.utils.RequestBodyParam;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.yldyhd.model.Cbqkqr;
import cn.hnisi.wx.server.service.yldyhd.model.Ffzhqr;
import cn.hnisi.wx.server.service.yldyhd.model.Sbxx;
import cn.hnisi.wx.server.ywserver.model.ResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class YldyhdController {

    @Resource
    private YldyhdService yldyhdService;

    /**
     * 检查是否允许办理
     * 若允许办理则查询申报信息返回
     * @return
     */
    @PostMapping("/api/frontend/service/yldyhd/checkIn")
    public ResponseEntity<Sbxx> checkIn(@RequestBodyParam String personId){
        return new ResponseEntity<>(yldyhdService.checkIn(personId));
    }

    /**
     * 查询参保情况确认信息
     * @return
     */
    @PostMapping("/api/frontend/service/yldyhd/cbqkqr")
    public ResponseEntity<Cbqkqr> queryCbqkqr(@RequestBodyParam String personId){
        return new ResponseEntity<>(yldyhdService.cbqkqr(personId));
    }

    /**
     * 查询发放账户确认信息
     * @return
     */
    @PostMapping("/api/frontend/service/yldyhd/ffzhqr")
    public ResponseEntity<Ffzhqr> yhxxqr(@RequestBodyParam String personId){
        return new ResponseEntity<>(yldyhdService.ffzhqr(personId));
    }

    /**
     * 提交业务申报
     * @return
     */
    @PostMapping("/api/frontend/service/yldyhd/submit")
    public ResponseEntity<String> submit(@RequestBodyParam String personId,@RequestBody Sbxx sbxx, User user){
        String orderNo = yldyhdService.submit(personId,sbxx,user);
        return new ResponseEntity<>(orderNo);
    }

    /**
     * 回写业务办理结果
     * @return
     */
    @PostMapping("/api/server/service/yldyhd/callback")
    public ResponseEntity callback(@RequestBody ResponseDTO responseDTO){
        yldyhdService.callback(responseDTO);
        return new ResponseEntity();
    }

}
