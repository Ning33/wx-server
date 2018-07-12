package cn.hnisi.wx.core.wxapi;


import cn.hnisi.wx.core.security.User;

public interface IWxService {

    User jscode2session(String jsCode);

}
