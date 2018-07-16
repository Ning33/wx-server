package cn.hnisi.wx.core.wxapi;


public interface IWxService {

    Jscode2sessionResponse jscode2session(String jsCode);

    class Jscode2sessionResponse{
        /**
         * 用户唯一标识
         */
        private String openid;
        /**
         * 调用微信接口使用的session
         */
        private String sessionKey;
        /**
         * 网关发布的凭证，用户识别依据
         */
        private String sessionid;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public String getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
            this.sessionid = sessionid;
        }
    }

}
