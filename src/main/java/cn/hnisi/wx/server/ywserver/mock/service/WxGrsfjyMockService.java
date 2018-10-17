package cn.hnisi.wx.server.ywserver.mock.service;

import cn.hnisi.wx.server.service.grsfjy.model.Sbxx;
import cn.hnisi.wx.server.ywserver.model.ExchangeData;
import cn.hnisi.wx.server.ywserver.model.RequestDTO;
import cn.hnisi.wx.server.ywserver.model.ResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class WxGrsfjyMockService {

    public ResponseDTO checkIn(RequestDTO requestDTO){

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(true);

        ExchangeData responseData = new ExchangeData();
        Sbxx sbxx = new Sbxx();
        sbxx.setAAC002("42011819861223152X");
        sbxx.setAAC003("测试人员001");
        sbxx.setAAC004("1");
        sbxx.setAAC006("19861223");
        sbxx.setAAC040(new BigDecimal(10000));
        sbxx.setAAC049("20170101");
        sbxx.setBAE001("东莞市");
        sbxx.setAAE140("10");

        Map<String,Object> gtData = new HashMap<>();
        gtData.put("sbxx",sbxx);
        responseData.setGtData(gtData);
        responseDTO.setResponseData(responseData);

        return responseDTO;
    }

    public ResponseDTO submit(RequestDTO requestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(true);
        responseDTO.setBae007("wxyw123011");
        return responseDTO;
    }
}
