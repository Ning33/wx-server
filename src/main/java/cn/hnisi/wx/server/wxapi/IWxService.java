package cn.hnisi.wx.server.wxapi;


public interface IWxService {

    class Jscode2sessionResponse{
        /**
         * 用户唯一标识
         */
        private String openid;
        /**
         * 调用微信接口使用的session
         */
        private String session_key;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getSession_key() {
            return session_key;
        }

        public void setSession_key(String session_key) {
            this.session_key = session_key;
        }
    }
    Jscode2sessionResponse jscode2session(String jsCode);

}
