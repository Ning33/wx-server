package cn.hnisi.wx.core.utils;

import java.util.UUID;

public class GuidUtil {

    public static String generate(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
