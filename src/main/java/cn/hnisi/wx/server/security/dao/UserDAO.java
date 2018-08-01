package cn.hnisi.wx.server.security.dao;

import cn.hnisi.wx.server.security.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDAO {

    void insert(User user);

    User query(@Param("openid") String openid);

}
