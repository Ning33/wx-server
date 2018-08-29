package cn.hnisi.wx.server.service.yldyhd;

import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.yldyhd.model.Cbqkqr;
import cn.hnisi.wx.server.service.yldyhd.model.Ffzhqr;
import cn.hnisi.wx.server.service.yldyhd.model.Sbxx;

public interface YldyhdService{

    /**
     * 业务准入校验
     * @param personId
     * @return
     */
    boolean checkIn(String personId);

    /**
     * 查询申报信息
     * @param personId
     * @return
     */
    Sbxx querySbxx(String personId);


    /**
     * 查询参保情况确认信息
     * @param personId
     * @return
     */
    Cbqkqr queryCbqkqr(String personId);

    /**
     * 查询发放账户确认信息
     * @param personId
     * @return
     */
    Ffzhqr queryFfzhqr(String personId);

    /**
     * 提交业务申报
     * @param personId
     * @return
     */
    String submit(String personId,Sbxx sbxx,User user);

}
