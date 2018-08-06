package cn.hnisi.wx.server.validateface;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 人脸详细日志 Dao
 */
@Mapper
public interface ValidateFaceDetailDao {

    /**
     * 首先插入没有图片和视频数据
     */
    void insertFirst(ValidateFaceLog validateFaceLog);

    /**
     * 查询未存入明细数据的token值
     * @return
     */
    List<String> queryTokenByFlag(@Param("host") String host);

    /**
     * 更新数据
     * @param validateFaceDetailLog
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void updateDetail(ValidateFaceDetailLog validateFaceDetailLog);

    /**
     * 更新十条数据,并标记机器码
     * @param machine
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void updateMachine(@Param("machine") String machine);
}
