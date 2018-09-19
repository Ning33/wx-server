package cn.hnisi.wx.server.dict;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.core.utils.RequestBodyParam;
import cn.hnisi.wx.server.dict.model.PackDict;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DictController {

    @Resource
    private DictService dictService;

    @PostMapping("/api/frontend/dict/update")
    public ResponseEntity<PackDict> isNeedUpdate(@RequestBodyParam String version){
        PackDict packDict = dictService.isNeedUpdate(version);
        return new ResponseEntity<>(packDict);
    }
}