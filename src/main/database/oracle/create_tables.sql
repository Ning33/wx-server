create table validate_face_log(
   token    varchar2(50),
   idcard   varchar2(20),
   name     varchar2(50),
   data     clob,
   log_time date default sysdate
);
comment on table validate_face_log is '人脸识别日志';
comment on column validate_face_log.token is '拉取信息凭证';
comment on column validate_face_log.idcard is '身份证号';
comment on column validate_face_log.name is '姓名';
comment on column validate_face_log.data is '详细数据';
comment on column validate_face_log.log_time is '日志记录时间戳';

--********************人脸识别日志详细信息s*********************--
create table validate_face_log_detail(
   token    varchar2(50),
   idcard   varchar2(20),
   name     varchar2(50),
   exist    int default 0,
   pic_1    varchar2(200),
   pic_2    varchar2(200),
   pic_3    varchar2(200),
   video    varchar2(200),
   machine  varchar2(50),
   log_time date default sysdate,
   data     clob
);
comment on table validate_face_log_detail is '人脸识别日志';
comment on column validate_face_log_detail.token is '拉取信息凭证';
comment on column validate_face_log_detail.idcard is '身份证号';
comment on column validate_face_log_detail.name is '姓名';
comment on column validate_face_log_detail.data is '详细数据';
comment on column validate_face_log_detail.exist is '是否已经存入明细日志, 1 :表示已存 ,2 :表示没有 ';
comment on column validate_face_log_detail.pic_1 is '照片数据地址_1';
comment on column validate_face_log_detail.pic_2 is '照片数据地址_2';
comment on column validate_face_log_detail.pic_3 is '照片数据地址_3';
comment on column validate_face_log_detail.video is '视频数据地址';
comment on column validate_face_log_detail.machine is '处理应用，记录具体哪一个应用处理';
comment on column validate_face_log_detail.log_time is '日志记录时间戳';

create table t_user(
   userid   varchar2(50) primary key,
   name     varchar2(50),
   idcard   varchar2(20),
   openid   varchar2(50),
   updated_time date default sysdate
);
alter table t_user add constraint u_user_openid unique(openid);
comment on table t_user is '用户信息';
comment on column t_user.userid is '用户ID';
comment on column t_user.name is '姓名';
comment on column t_user.idcard is '公民身份证号';
comment on column t_user.openid is '微信用户ID';
comment on column t_user.updated_time is '数据更新时间戳';


create table t_person(
   personid    varchar2(50) primary key ,
   userid      varchar2(50),
   is_self     varchar2(1),
   name        varchar2(50),
   idcard      varchar2(50),
   birthday    varchar2(8),
   sex         varchar2(1),
   tel         varchar2(20),
   address     varchar2(200),
   sicard      varchar2(50),
   updated_time   date default sysdate
);
alter table t_user add constraint u_person_openid_idcard unique(openid,idcard);
comment on table t_person is '人员信息表';
comment on column t_person.personid is '人员ID';
comment on column t_person.userid is '用户ID';
comment on column t_person.name is '姓名';
comment on column t_person.idcard is '公民身份证号码';
comment on column t_person.birthday is '出生日期';
comment on column t_person.sex is '性别,1男,2女,9未知';
comment on column t_person.tel is '联系电话';
comment on column t_person.address is '联系地址';
comment on column t_person.sicard is '社保卡号';
comment on column t_person.updated_time is '数据更新时间戳';