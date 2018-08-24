package cn.hnisi.wx.server.dict;

import cn.hnisi.wx.server.dict.model.PackDict;

public interface DictService {
    /**
     * 是否需要更新
     * @param version
     * @return
     */
    PackDict isNeedUpdate(String version);
}
