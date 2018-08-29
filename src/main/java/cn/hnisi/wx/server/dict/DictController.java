package cn.hnisi.wx.server.dict;

import cn.hnisi.wx.core.io.ResponseEntity;
import cn.hnisi.wx.server.dict.model.PackDict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DictController {

    @Resource
    private DictService dictService;

    @RequestMapping("/api/frontend/dict/update")
    public ResponseEntity<PackDict> isNeedUpdate(String version){
        PackDict packDict = dictService.isNeedUpdate(version);
        return new ResponseEntity<>(packDict);
    }
}