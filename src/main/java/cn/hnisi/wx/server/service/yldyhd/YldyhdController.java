package cn.hnisi.wx.server.service.yldyhd;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.ServiceUtil;
import cn.hnisi.wx.server.service.model.ServiceResult;
import cn.hnisi.wx.server.service.yldyhd.model.Cbqkqr;
import cn.hnisi.wx.server.service.yldyhd.model.Ffzhqr;
import cn.hnisi.wx.server.service.yldyhd.model.Sbxx;
import cn.hnisi.wx.server.service.yldyhd.model.YldyhdResult;
import cn.hnisi.wx.server.validateface.ValidateFaceService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public ResponseEntity<Sbxx> checkIn(String personid, User user){
        Sbxx response = new Sbxx();
        response.setAAC002("42011819861223152X");
        response.setAAC003("测试人员001");
        response.setAAE010("666666666");
        return new ResponseEntity<>(response);
    }

    /**
     * 查询参保情况确认信息
     * @return
     */
    @RequestMapping("/api/frontend/service/yldyhd/step-cbqkqr-query")
    public ResponseEntity<Cbqkqr> queryCbqkqr(String personid, User user){
        Cbqkqr response = new Cbqkqr();
        response.setStjfys(80);
        response.setSjjfys(100);
        response.setHjjfys(180);
        response.setSjzhje(new BigDecimal(10000));
        response.setStzhje(new BigDecimal(80040.23));
        response.setHjjfje(new BigDecimal(90040.23));

        return new ResponseEntity<>(response);
    }

    /**
     * 查询发放账户确认信息
     * @return
     */
    @RequestMapping("/api/frontend/service/yldyhd/step-ffzhqr-query")
    public ResponseEntity<Ffzhqr> yhxxqr(String personid, User user){
        Ffzhqr response = new Ffzhqr();
        response.setAAE133("张三");
        response.setAAE010("888888888888");
        response.setAAE195("东莞银行广州分行");
        return new ResponseEntity<>(response);
    }

    /**
     * 提交业务申报
     * @return
     */
    @RequestMapping("/api/frontend/service/yldyhd/submit")
    public ResponseEntity<String> submit(@RequestBody RequestEntity<Sbxx> entity, User user){
        String personid = entity.getPersonid();
        Sbxx sbxx = entity.getSbxx();
        //从订单中获取参保人信息
        String orderno = yldyhdService.submit(personid,sbxx,user);
        return new ResponseEntity<>(orderno);
    }

    /**
     * 回写业务办理结果
     * @return
     */
    @RequestMapping("/api/server/service/yldyhd/callback")
    public ResponseEntity callback(@RequestBody ServiceResult<YldyhdResult> serviceResult){
        serviceUtil.update(serviceResult);
        return new ResponseEntity();
    }

}

/**
 * 临时使用，后续调整
 * TODO
 * @param <T>
 */
class RequestEntity<T>{
    private String personid;
    private T sbxx;

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public T getSbxx() {
        return sbxx;
    }

    public void setSbxx(T sbxx) {
        this.sbxx = sbxx;
    }
}