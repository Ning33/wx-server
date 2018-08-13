package cn.hnisi.wx.server.service.yldyhd;

import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.yldyhd.model.CbqkqrResponse;
import cn.hnisi.wx.server.service.yldyhd.model.FfzhqrResponse;
import cn.hnisi.wx.server.service.yldyhd.model.SbxxResponse;

public interface YldyhdService{

    /**
     * 业务准入校验
     * @param personid
     * @return
     */
    boolean checkIn(String personid);

    /**
     * 查询申报信息
     * @param personid
     * @return
     */
    SbxxResponse querySbxx(String personid);

    /**
     * 提交申报信息并初始化流程
     * @return 受理单号
     */
    String submitSbxx(String personid,User user);

    /**
     * 查询参保情况确认信息
     * @param orderno
     * @return
     */
    CbqkqrResponse queryCbqkqr(String orderno);

    /**
     * 查询发放账户确认信息
     * @param orderno
     * @return
     */
    FfzhqrResponse queryFfzhqr(String orderno);

    /**
     * 提交业务申报
     * @param orderno
     * @return
     */
    void submit(String orderno);

}
