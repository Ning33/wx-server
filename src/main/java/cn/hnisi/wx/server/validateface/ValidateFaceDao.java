package cn.hnisi.wx.server.validateface;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ValidateFaceDao {

    void insert(ValidateFaceLog validateFaceLog);

    ValidateFaceLog queryByToken(@Param("token") String token);

    List<ValidateFaceLog> queryByIdcard(@Param("idcard") String idcard);

}
