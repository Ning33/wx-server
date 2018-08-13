package cn.hnisi.wx.server.service;

import cn.hnisi.wx.core.utils.GuidUtil;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class OrderNoGenerator {

    /**
     * 生成受理单号
     * 业务规则：14位时间拼上4为uuid
     * @return
     */
    public String generate(){
        Calendar calendar = Calendar.getInstance();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
        return timestamp+GuidUtil.generate().substring(28);
    }
}
