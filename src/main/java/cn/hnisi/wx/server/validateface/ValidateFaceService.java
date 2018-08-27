package cn.hnisi.wx.server.validateface;

import cn.hnisi.wx.core.exception.AppException;
import cn.hnisi.wx.core.exception.ValidateFaceException;
import cn.hnisi.wx.core.io.ResponseStatus;
import cn.hnisi.wx.core.utils.FTPclientEntity;
import cn.hnisi.wx.core.utils.JsonUtil;
import cn.hnisi.wx.server.properties.FtpProperties;
import cn.hnisi.wx.server.properties.ValidateFaceProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Base64.Decoder;
import java.util.concurrent.TimeUnit;

@Service
public class ValidateFaceService {

    private static final Logger logger = LoggerFactory.getLogger(ValidateFaceService.class);

    private static final String VALIDATE_FACE_TOKEN = "validateFace:";

    @Resource
    private ValidateFaceProperties validateFaceProperties;

    @Resource
    private RestTemplate restTemplate;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Resource
    private ValidateFaceDao validateFaceDao;

    /**
     * 上传ftp属性设置
     */
    @Resource
    private FtpProperties ftpProperties;

    /**
     * 详细人脸日志
     */
    @Resource
    private ValidateFaceDetailDao validateFaceDetailDao;


    @Transactional
    public void saveToken(String token) throws AppException{
        //拉取token信息
        GetDetectInfoResponse response = getDetectInfo(token);

        //保存至redis中
        redisTemplate.opsForValue().set(VALIDATE_FACE_TOKEN+token,JsonUtil.convertBeanToJson(response),60, TimeUnit.MINUTES);

        //保存至数据库中
        ValidateFaceLog validateFaceLog = new ValidateFaceLog();
        validateFaceLog.setToken(token);
        validateFaceLog.setIdcard(response.getID());
        validateFaceLog.setName(response.getName());
        validateFaceLog.setData(JsonUtil.convertBeanToJson(response));
        validateFaceDao.insert(validateFaceLog);
        //在把数据存入明细表中
        validateFaceDetailDao.insertFirst(validateFaceLog);
    }

    /**
     * 定时存入(每天凌晨二点 开始执行存入操作 cron = ("0 0 2 * * ?")
     * @throws AppException
     */
    @Scheduled(cron = ("0 0 2 * * ?") )
    public void startSaveDetail() throws AppException{
        while(true){
            //获取当前时间
            Calendar time = Calendar.getInstance();
            //每天七点停止存入
            if(time.get(Calendar.HOUR_OF_DAY) <= ftpProperties.getStopSaveTime()){
                //上传数据
                int num = saveTokenDetail(ftpProperties.getSaveNumberOnce());

                if(num == 0 ){
                    Timer timer = new Timer();
                    //延迟一个小时后执行
                    TimerTask task = new TimerTask(){
                        @Override
                        public void run() {
                            logger.info("延迟后再次执行:");
                            startSaveDetail();
                        }
                    };
                    timer.schedule(task,ftpProperties.getDelayTime());
                    break;
                }
            }else{
                logger.info("停止存入");
                break;
            }
        }

    }
    /**
     * 存入 人脸详细日志
     *
     * @throws AppException
     */
    @Transactional
    public int saveTokenDetail(int number) throws AppException {
        //已处理的数据次数
        int handleNum = 0;

        //开始更新数据 ,并标记机器码
        validateFaceDetailDao.updateMachine(ftpProperties.getMachineId(),number);

        //读取没有插入明细数据 且 标识为本机IP的token值
        List<String> listToken = validateFaceDetailDao.queryTokenByFlag(ftpProperties.getMachineId());
        //遍历查询出来的token值
        for (String token : listToken) {
            try{
                //如果取值不为空
                if (!StringUtils.isEmpty(token)) {
                    //拉取token信息
                    GetDetectInfoResponse response = getDetectInfo(token, true);
                    //先更新data字段
                    ValidateFaceDetailLog validateFaceDetailLog = new ValidateFaceDetailLog();
                    validateFaceDetailLog.setToken(token);
                    validateFaceDetailLog.setData(JsonUtil.convertBeanToJson(response));
                    //更新明细表 data字段  加了新事物
                    validateFaceDetailDao.updateDetail(validateFaceDetailLog);

                    //定义String数组 接受图片视频等
                    String[] strs = new String[4];
                    strs[0] = response.getVideopic1();
                    strs[1] = response.getVideopic2();
                    strs[2] = response.getVideopic3();
                    strs[3] = response.getVideo();
                    //base64字符串转化成图片上传到FTP , 注意请关闭防火墙
                    List<String> paths =  GenerateImageToFTP(strs, token);
                    //上传ftp成功之后更新明细数据

                    validateFaceDetailLog.setPic_1(paths.get(0));
                    validateFaceDetailLog.setPic_2(paths.get(1));
                    validateFaceDetailLog.setPic_3(paths.get(2));
                    validateFaceDetailLog.setVideo(paths.get(3));
                    //改变存入状态
                    validateFaceDetailLog.setExist(1);
                    //更新数据库
                    validateFaceDetailDao.updateDetail(validateFaceDetailLog);
                    logger.info("更新成功!");
                }
            } catch (Exception e){
                e.printStackTrace();
                //回滚机器码 重置为空
                validateFaceDetailDao.fallbackMachineId(token);
                throw new AppException(ResponseStatus.GENERATE_ImageToFTP);
            } finally {
                //处理次数
                handleNum++;
            }

        }

        return handleNum;
    }

    /**
     * 测试使用，保存永久有效的人脸token
     * @param token
     * @param json
     */
    public void saveToken(String token,String json){
        redisTemplate.opsForValue().set(VALIDATE_FACE_TOKEN+token,json);
    }



    //base64字符串转化成图片上传到FTP
    public List<String> GenerateImageToFTP(String[] strs,  String token) {
        //存放路径
        List<String> listPath = new ArrayList<>();
        //连接ftp服务器
        FTPclientEntity fTPclientEntity = new FTPclientEntity();
        FTPClient ftp = fTPclientEntity.getConnectionFTP(ftpProperties.getHostname(),ftpProperties.getPort(),ftpProperties.getUsername(), ftpProperties.getPassword());
        //设置上传路径
        String path_ftp = ftpProperties.getPath_ftp() + token;

        //对strs遍历
        for (int i = 0; i < strs.length; i++) {
            //对字节数组字符串进行Base64解码并生成图片
            if (strs[i] == null) {
                throw new AppException(ResponseStatus.GENERATE_ImageToFTP);
            }
            // 获取解密对象
            Decoder decoder = Base64.getDecoder();
            //Base64解码
            byte[] b = null;
            try {
                b = decoder.decode(strs[i]);
                for (int j = 0; j < b.length; ++j) {
                    if (b[j] < 0) {//调整异常数据
                        b[j] += 256;
                    }
                }
                //创建输入流
                InputStream fis = new ByteArrayInputStream(b);
                //文件名
                String fileName;
                if (i <= 2) {  //上传图片文件
                    fileName = i + ftpProperties.getImgPath_suf_jpg();
                    //上传文件
                    fTPclientEntity.uploadFile(ftp, path_ftp, fileName, fis);
                } else {  //上传视频文件
                    fileName = i + ftpProperties.getVideoPath_suf();
                    fTPclientEntity.uploadFile(ftp, path_ftp, fileName, fis);
                }
                listPath.add(path_ftp + "/" + fileName);

            } catch (Exception e) {
                e.printStackTrace();
                throw new AppException(ResponseStatus.GENERATE_ImageToFTP);
            }
        }
        //退出ftp
        fTPclientEntity.closeFTP(ftp);

        return listPath;
    }

    /**
     * 验证token是否有效
     * @param request
     * @param idcard
     * @param name
     * @return
     */
    public String validateToken(HttpServletRequest request, String idcard, String name){
        String token = request.getHeader("x-tif-validate-face-"+idcard);

        //从redis中获取token
        String tokenStr = redisTemplate.opsForValue().get(VALIDATE_FACE_TOKEN+token);
        if(StringUtils.isEmpty(tokenStr)){
            throw new ValidateFaceException(idcard,name);
        }

        //获取token信息，判断证件号码是否相同
        GetDetectInfoResponse response = JsonUtil.convertJsonToBean(tokenStr,GetDetectInfoResponse.class);
        if(!response.getID().equals(idcard)){
            throw new ValidateFaceException(idcard,name,"证件号码不符");
        }

        //判断姓名是否相同
        if(!response.getName().equals(name)){
            throw new ValidateFaceException(idcard,name,"姓名不符");
        }

        return token;
    }

    public String signature(String apiName) {
        String secret = validateFaceProperties.getSecretKey();
        String appid = validateFaceProperties.getAppid();
        String now = String.valueOf(new Date().getTime()/1000);
        int signExpired = validateFaceProperties.getExpired();

        String originStr = "a="+appid+"&m="+apiName+"&t="+now+"&e="+signExpired;

        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA1");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e ) {
            throw new AppException("签名异常",e);
        }

        byte[] originBytes = mac.doFinal(originStr.getBytes());
        byte[] mixBytes = ArrayUtils.addAll(originBytes,originStr.getBytes());
        return Base64.getEncoder().encodeToString(mixBytes);
    }

    static class GetDetectInfoResponse{
        @JsonProperty("ID")
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
    public GetDetectInfoResponse getDetectInfo(String token){
        return getDetectInfo(token,false);
    }
    public GetDetectInfoResponse getDetectInfo(String token,boolean getDetail) {
        String url = "https://iauth-sandbox.wecity.qq.com/new/cgi-bin/getdetectinfo.php";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("signature",this.signature("getdetectinfo"));

        Map<String,Object> inMap = new HashMap<>();
        inMap.put("token",token);
        inMap.put("appid",validateFaceProperties.getAppid());
        inMap.put("crypt_type",3);
        inMap.put("info_type",getDetail?0:1);

        HttpEntity<Map> request = new HttpEntity<>(inMap,headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,request,String.class);
        String jsonStr = response.getBody();
        Map dataMap = JsonUtil.convertJsonToBean(jsonStr,Map.class);

        int errorcode = (int) dataMap.get("errorcode");
        String errormsg = (String) dataMap.get("errormsg");
        if(errorcode != 0){
            throw new AppException(ResponseStatus.DATA_VALIDATE_EXCEPTION,errormsg);
        }
        String data = (String) dataMap.get("data");

        //解密
        try{
            String secret = validateFaceProperties.getResultKey();
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "AES");
            byte[] bytes = Base64.getDecoder().decode(data);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,signingKey);
            byte[] result = cipher.doFinal(bytes);
            String json = new String(result);
            return JsonUtil.convertJsonToBean(json,GetDetectInfoResponse.class);
        }catch(Exception e){
            throw new AppException(e);
        }

    }
}
