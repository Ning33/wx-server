package cn.hnisi.wx.core.wxapi;

import cn.hnisi.wx.core.security.User;
import org.springframework.stereotype.Service;

@Service
public class WxServiceImpl implements IWxService {
    @Override
    public User jscode2session(String jsCode) {
        return null;
    }
}
