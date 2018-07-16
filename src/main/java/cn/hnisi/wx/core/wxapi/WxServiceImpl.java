package cn.hnisi.wx.core.wxapi;

import cn.hnisi.wx.core.WxProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxServiceImpl implements IWxService {

    @Resource
    private WxProperties wxProperties;

    @Override
    public Jscode2sessionResponse jscode2session(String jsCode) {
        //TODO TEST
        Jscode2sessionResponse user = new Jscode2sessionResponse();
        user.setOpenid("123");
        user.setSessionKey("333");
        return user;
    }
}
