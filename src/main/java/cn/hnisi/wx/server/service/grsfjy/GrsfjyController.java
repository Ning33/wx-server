package cn.hnisi.wx.server.service.grsfjy;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.utils.RequestBodyParam;
import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.grsfjy.model.Sbxx;
import cn.hnisi.wx.server.ywserver.model.AttachFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class GrsfjyController {

    @Resource
    private GrsfjyService grsfjyService;

    /**
     * 检查是否允许办理
     * 若允许办理则查询申报信息返回
     * @return
     */
    @PostMapping("/api/frontend/service/grsfjy/checkIn")
    public ResponseEntity<Sbxx> checkIn(@RequestBodyParam String personId){
        return new ResponseEntity<>(grsfjyService.checkIn(personId));
    }

    /**
     * 提交业务申报
     * @return
     */
    @PostMapping("/api/frontend/service/grsfjy/submit")
    public ResponseEntity<String> submit(@RequestBodyParam String personId, @RequestBody AttachFile attachFile, User user){
        String orderNo = grsfjyService.submit(personId,attachFile,user);
        return new ResponseEntity<>(orderNo);
    }

    /**
     * 回写业务办理结果
     * @return
     */
    /*@PostMapping("/api/server/service/grsfjy/callback")
    public ResponseEntity callback(@RequestBody ResponseDTO responseDTO){
        grsfjyService.callback(responseDTO);
        return new ResponseEntity();
    }*/
}
