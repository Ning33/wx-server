package cn.hnisi.wx.server.ywserver.mock.service;

import cn.hnisi.wx.server.service.yldyhd.model.Cbqkqr;
import cn.hnisi.wx.server.service.yldyhd.model.Ffzhqr;
import cn.hnisi.wx.server.service.yldyhd.model.Sbxx;
import cn.hnisi.wx.server.ywserver.model.ExchangeData;
import cn.hnisi.wx.server.ywserver.model.RequestDTO;
import cn.hnisi.wx.server.ywserver.model.ResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class YldyhdMockService {

    public ResponseDTO checkIn(RequestDTO requestDTO){

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(true);

        ExchangeData responseData = new ExchangeData();
        Sbxx sbxx = new Sbxx();
        sbxx.setAAC002("42011819861223152X");
        sbxx.setAAC003("测试人员001");
        sbxx.setAAE010("666666666");
        Map<String,Object> gtData = new HashMap<>();
        gtData.put("sbxx",sbxx);
        responseData.setGtData(gtData);
        responseDTO.setResponseData(responseData);

        return responseDTO;
    }

    public ResponseDTO cbqkqr(RequestDTO requestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(true);

        ExchangeData responseData = new ExchangeData();
        Cbqkqr response = new Cbqkqr();
        response.setStjfys(80);
        response.setSjjfys(100);
        response.setHjjfys(180);
        response.setSjzhje(new BigDecimal(10000));
        response.setStzhje(new BigDecimal(80040.23));
        response.setHjjfje(new BigDecimal(90040.23));
        Map<String,Object> gtData = new HashMap<>();
        gtData.put("sbxx",response);
        responseData.setGtData(gtData);
        responseDTO.setResponseData(responseData);

        return responseDTO;
    }

    public ResponseDTO ffzhqr(RequestDTO requestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(true);

        ExchangeData responseData = new ExchangeData();
        Ffzhqr response = new Ffzhqr();
        response.setAAE133("张三");
        response.setAAE010("888888888888");
        response.setAAE195("东莞银行广州分行");
        Map<String,Object> gtData = new HashMap<>();
        gtData.put("sbxx",response);
        responseData.setGtData(gtData);
        responseDTO.setResponseData(responseData);

        return responseDTO;
    }

    public ResponseDTO submit(RequestDTO requestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccess(true);
        return responseDTO;
    }
}
