package cn.hnisi.wx.server.service.yldyhd;

import cn.hnisi.wx.server.security.model.User;
import cn.hnisi.wx.server.service.yldyhd.model.Cbqkqr;
import cn.hnisi.wx.server.service.yldyhd.model.Ffzhqr;
import cn.hnisi.wx.server.service.yldyhd.model.Sbxx;

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
    Sbxx querySbxx(String personid);


    /**
     * 查询参保情况确认信息
     * @param personid
     * @return
     */
    Cbqkqr queryCbqkqr(String personid);

    /**
     * 查询发放账户确认信息
     * @param personid
     * @return
     */
    Ffzhqr queryFfzhqr(String personid);

    /**
     * 提交业务申报
     * @param personid
     * @return
     */
    String submit(String personid,Sbxx sbxx,User user);

}
