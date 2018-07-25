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