package cn.hnisi.wx.server.filter.dao;

import cn.hnisi.wx.server.filter.model.Api;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApiDAO {
    /**
     * 根据uri查询安全等级
     */
    Api querySecurityByUrl(@Param("url") String url);
}
