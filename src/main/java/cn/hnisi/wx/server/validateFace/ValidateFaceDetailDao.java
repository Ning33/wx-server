package cn.hnisi.wx.server.validateFace;

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
    List<String> queryTokenByFlag(@Param("machineId") String machineId);

    /**
     * 更新数据
     * @param validateFaceDetailLog
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void updateDetail(ValidateFaceDetailLog validateFaceDetailLog);

    /**
     * 更新十条数据,并标记机器码
     * @param machineId
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void updateMachine(@Param("machineId") String machineId,@Param("number") int number);

    /**
     * 根据token回滚机器码 重置为空
     * @param token
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void fallbackMachineId(@Param("token") String token);
}
