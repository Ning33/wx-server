package cn.hnisi.wx.server.validateface;

import org.apache.ibatis.annotations.Mapper;
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
    List<String> queryTokenByFlag();

    /**
     * 更新数据
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void updateDetail(ValidateFaceDetailLog validateFaceDetailLog);


}
