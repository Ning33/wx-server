package cn.hnisi.wx.server.service.yldyhd;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.utils.RequestBodyParam;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.ServiceUtil;
import cn.hnisi.wx.server.service.model.ServiceResult;
import cn.hnisi.wx.server.service.yldyhd.model.Cbqkqr;
import cn.hnisi.wx.server.service.yldyhd.model.Ffzhqr;
import cn.hnisi.wx.server.service.yldyhd.model.Sbxx;
import cn.hnisi.wx.server.service.yldyhd.model.YldyhdResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class YldyhdController {

    @Resource
    private YldyhdService yldyhdService;

    @Resource
    private ServiceUtil serviceUtil;

    /**
     * 检查是否存在未办结的业务
     * @param user
     * @return
     */
    @PostMapping("/api/frontend/service/yldyhd/pre-check")
    public ResponseEntity preCheck(User user){
        // 查询是否存在未办结业务


        return new ResponseEntity();
    }

    /**
     * 检查是否允许办理
     * 若允许办理则查询申报信息返回
     * @return
     */
    @PostMapping("/api/frontend/service/yldyhd/check-in")
    public ResponseEntity<Sbxx> checkIn(@RequestBodyParam String personId, User user){
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
    @PostMapping("/api/frontend/service/yldyhd/step-cbqkqr-query")
    public ResponseEntity<Cbqkqr> queryCbqkqr(@RequestBodyParam String personId, User user){
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
    @PostMapping("/api/frontend/service/yldyhd/step-ffzhqr-query")
    public ResponseEntity<Ffzhqr> yhxxqr(@RequestBodyParam String personId, User user){
        Ffzhqr response = new Ffzhqr();
        response.setAAE133("张三");
        response.setAAE010("888888888888");
        response.setAAE195("东莞银行广州分行");
        return new ResponseEntity<>(response);
    }


    public static class SubmitRequest extends Sbxx{

        private String personId;
        @JsonProperty("personId")
        public String getPersonId() {
            return personId;
        }
        @JsonProperty("personId")
        public void setPersonId(String personId) {
            this.personId = personId;
        }
    }
    /**
     * 提交业务申报
     * @return
     */
    @PostMapping("/api/frontend/service/yldyhd/submit")
    public ResponseEntity<String> submit(@RequestBody SubmitRequest request, User user){
        String personId = request.getPersonId();
        Sbxx sbxx = new Sbxx();
        BeanUtils.copyProperties(request,sbxx);
        //从订单中获取参保人信息
        String orderNo = yldyhdService.submit(personId,sbxx,user);
        return new ResponseEntity<>(orderNo);
    }

    /**
     * 回写业务办理结果
     * @return
     */
    @PostMapping("/api/server/service/yldyhd/callback")
    public ResponseEntity callback(@RequestBody ServiceResult<YldyhdResult> serviceResult){
        serviceUtil.update(serviceResult);
        return new ResponseEntity();
    }

}
