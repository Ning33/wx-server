package cn.hnisi.wx.server.ywserver.model;

import java.util.Map;
import java.util.List;

public class ExchangeData {
    private Map<String,Object> gtData;
    private List<AttachFile> attachFiles;

    public Map<String, Object> getGtData() {
        return gtData;
    }

    public void setGtData(Map<String, Object> gtData) {
        this.gtData = gtData;
    }

    public List<AttachFile> getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(List<AttachFile> attachFiles) {
        this.attachFiles = attachFiles;
    }
}
