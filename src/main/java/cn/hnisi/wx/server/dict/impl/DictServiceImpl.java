package cn.hnisi.wx.server.dict.impl;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.server.dict.DictService;
import cn.hnisi.wx.server.dict.dao.DictDao;
import cn.hnisi.wx.server.dict.model.Dict;
import cn.hnisi.wx.server.dict.model.PackDict;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DictServiceImpl implements DictService{

    @Resource
    private DictDao dictDao;
    /**
     * 是否需要更新
     * @param version
     * @return
     */
    @Override
    public PackDict isNeedUpdate(String version) {
        if(StringUtils.isEmpty(version)){
            throw new  AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,"数据为空");
        }

        boolean needUpdate;
        PackDict packDict = new PackDict();
        //查询版本增量的字典配置项
        List<Dict> dicts= dictDao.queryPlusDict(version);

        if(dicts.isEmpty()||dicts.size() == 0){  //说明没有版本更新
            needUpdate = false;
            packDict.setNeedUpdate(needUpdate);
            packDict.setData("noUpdate");
        }else{      //更新字典配置项
            needUpdate = true;
            packDict.setNeedUpdate(needUpdate);
            packDict.setData(dicts);
        }
        return packDict;
    }
}
