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

    /**
     * 获取前端需要的签名
     * @return
     */
    String signature(String apiName);

    class GetDetectInfoResponse{
        private String ID;
        private String name;
        private String phone;
        private String sex;
        private String nation;
        private String ID_address;
        private String ID_birth;
        private String ID_authority;
        private String ID_valid_date;
        private String validatedata;
        private String frontpic;
        private String backpic;
        private String videopic1;
        private String videopic2;
        private String videopic3;
        private String video;
        private int yt_errorcode;
        private String yt_errormsg;
        private int livestatus;
        private String livemsg;
        private int comparestatus;
        private String comparemsg;
        private int type;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getID_address() {
            return ID_address;
        }

        public void setID_address(String ID_address) {
            this.ID_address = ID_address;
        }

        public String getID_birth() {
            return ID_birth;
        }

        public void setID_birth(String ID_birth) {
            this.ID_birth = ID_birth;
        }

        public String getID_authority() {
            return ID_authority;
        }

        public void setID_authority(String ID_authority) {
            this.ID_authority = ID_authority;
        }

        public String getID_valid_date() {
            return ID_valid_date;
        }

        public void setID_valid_date(String ID_valid_date) {
            this.ID_valid_date = ID_valid_date;
        }

        public String getValidatedata() {
            return validatedata;
        }

        public void setValidatedata(String validatedata) {
            this.validatedata = validatedata;
        }

        public String getFrontpic() {
            return frontpic;
        }

        public void setFrontpic(String frontpic) {
            this.frontpic = frontpic;
        }

        public String getBackpic() {
            return backpic;
        }

        public void setBackpic(String backpic) {
            this.backpic = backpic;
        }

        public String getVideopic1() {
            return videopic1;
        }

        public void setVideopic1(String videopic1) {
            this.videopic1 = videopic1;
        }

        public String getVideopic2() {
            return videopic2;
        }

        public void setVideopic2(String videopic2) {
            this.videopic2 = videopic2;
        }

        public String getVideopic3() {
            return videopic3;
        }

        public void setVideopic3(String videopic3) {
            this.videopic3 = videopic3;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public int getYt_errorcode() {
            return yt_errorcode;
        }

        public void setYt_errorcode(int yt_errorcode) {
            this.yt_errorcode = yt_errorcode;
        }

        public String getYt_errormsg() {
            return yt_errormsg;
        }

        public void setYt_errormsg(String yt_errormsg) {
            this.yt_errormsg = yt_errormsg;
        }

        public int getLivestatus() {
            return livestatus;
        }

        public void setLivestatus(int livestatus) {
            this.livestatus = livestatus;
        }

        public String getLivemsg() {
            return livemsg;
        }

        public void setLivemsg(String livemsg) {
            this.livemsg = livemsg;
        }

        public int getComparestatus() {
            return comparestatus;
        }

        public void setComparestatus(int comparestatus) {
            this.comparestatus = comparestatus;
        }

        public String getComparemsg() {
            return comparemsg;
        }

        public void setComparemsg(String comparemsg) {
            this.comparemsg = comparemsg;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
    GetDetectInfoResponse getDetectInfo(String token);

}
