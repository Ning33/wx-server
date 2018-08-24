package cn.hnisi.wx.server.dict.dao;

import cn.hnisi.wx.server.dict.model.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictDao {
    /**
     * 查询所有配置项
     * @return
     */
    List<Dict> queryPlusDict(@Param("version") String version);

}
