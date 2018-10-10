create table t_validate_face_log(
   token    varchar2(50),
   idcard   varchar2(20),
   name     varchar2(50),
   data     clob,
   logTime date default sysdate
);
comment on table t_validate_face_log is '人脸识别日志';
comment on column t_validate_face_log.token is '拉取信息凭证';
comment on column t_validate_face_log.idcard is '身份证号';
comment on column t_validate_face_log.name is '姓名';
comment on column t_validate_face_log.data is '详细数据';
comment on column t_validate_face_log.logTime is '日志记录时间戳';

--********************人脸识别日志详细信息s*********************--
create table t_validate_face_log_detail(
   token    varchar2(50),
   idcard   varchar2(20),
   name     varchar2(50),
   exist    int default 0,
   pic_1    varchar2(200),
   pic_2    varchar2(200),
   pic_3    varchar2(200),
   video    varchar2(200),
   machine  varchar2(50),
   logTime date default sysdate,
   data     clob
);
comment on table t_validate_face_log_detail is '人脸识别详细日志';
comment on column t_validate_face_log_detail.token is '拉取信息凭证';
comment on column t_validate_face_log_detail.idcard is '身份证号';
comment on column t_validate_face_log_detail.name is '姓名';
comment on column t_validate_face_log_detail.data is '详细数据';
comment on column t_validate_face_log_detail.exist is '是否已经存入明细日志, 1 :表示已存 ,2 :表示没有 ';
comment on column t_validate_face_log_detail.pic_1 is '照片数据地址_1';
comment on column t_validate_face_log_detail.pic_2 is '照片数据地址_2';
comment on column t_validate_face_log_detail.pic_3 is '照片数据地址_3';
comment on column t_validate_face_log_detail.video is '视频数据地址';
comment on column t_validate_face_log_detail.machine is '处理应用，记录具体哪一个应用处理';
comment on column t_validate_face_log_detail.logTime is '日志记录时间戳';

create table t_user(
   userId   varchar2(50) primary key,
   name     varchar2(50),
   idcard   varchar2(20),
   openid   varchar2(50),
   updatedTime date default sysdate
);
alter table t_user add constraint u_user_openid unique(openid);
comment on table t_user is '用户信息';
comment on column t_user.userId is '用户ID';
comment on column t_user.name is '姓名';
comment on column t_user.idcard is '公民身份证号';
comment on column t_user.openid is '微信用户ID';
comment on column t_user.updatedTime is '数据更新时间戳';


create table t_person(
   personId    varchar2(50) primary key ,
   userId      varchar2(50),
   isSelf      varchar2(1),
   name        varchar2(50),
   idcard      varchar2(50),
   birthday    varchar2(8),
   sex         varchar2(1),
   tel         varchar2(20),
   address     varchar2(200),
   sicard      varchar2(50),
   updatedTime   date default sysdate
);
alter table t_person add constraint u_person_userId_idcard unique(userId,idcard);
comment on table t_person is '人员信息表';
comment on column t_person.personId is '人员ID';
comment on column t_person.userId is '用户ID';
comment on column t_person.name is '姓名';
comment on column t_person.idcard is '公民身份证号码';
comment on column t_person.birthday is '出生日期';
comment on column t_person.sex is '性别,1男,2女,9未知';
comment on column t_person.tel is '联系电话';
comment on column t_person.address is '联系地址';
comment on column t_person.sicard is '社保卡号';
comment on column t_person.updatedTime is '数据更新时间戳';

create table t_order(
   orderNo     varchar2(50) primary key ,
   serviceId   varchar2(50),
   serviceName varchar2(50),
   userId      varchar2(50),
   userIdcard  varchar2(20),
   userName    varchar2(50),
   personId    varchar2(50),
   personIdcard   varchar2(20),
   personName  varchar2(50),
   status      varchar2(2),
   requestData    clob,
   responseData   clob,
   createdTime   date default sysdate,
   updatedTime   date default sysdate,
   completionTime date
);
comment on table t_order is '受理单信息';
comment on column t_order.orderNo is '受理单号';
comment on column t_order.serviceId is '服务事项ID';
comment on column t_order.serviceName is '服务事项名称';
comment on column t_order.userId is '用户id';
comment on column t_order.userIdcard is '用户公民身份证号';
comment on column t_order.userName is '用户姓名';
comment on column t_order.personId is '参保人ID';
comment on column t_order.personIdcard is '参保人公民身份证号码';
comment on column t_order.personName is '参保人姓名';
comment on column t_order.status is '业务状态，0申报中，10审核中，21审核成功,22审核失败';
comment on column t_order.requestData is '业务请求信息，json格式存储';
comment on column t_order.responseData is '业务响应信息，json格式存储';
comment on column t_order.createdTime is '创建时间';
comment on column t_order.updatedTime is '更新时间';
comment on column t_order.completionTime is '完成时间';

create table t_service
(
   serviceId    VARCHAR2(50) primary key,
   name         VARCHAR2(50),
   title        VARCHAR2(50),
   description  VARCHAR2(200),
   catalog      varchar2(20),
   securityLevel varchar2(1),
   orderNum         number
);
comment on table t_service is '事项配置表';
comment on column t_service.serviceId is '事项ID';
comment on column t_service.name is '事项名称';
comment on column t_service.title is '事项标题';
comment on column t_service.description is '事项概要';
comment on column t_service.catalog is '事项类别: bxgx-征缴业务,yldy-养老待遇,gsdy-工伤待遇,syedy-失业待遇,yildy-医疗待遇';
comment on column t_service.securityLevel is '安全级别';
comment on column t_service.orderNum is '排序作用';

create table t_dictionary(
       field      varchar2(20)  not null ,
       dictValue      varchar2(20) not null ,
       dictDisplay      varchar2(20) not null ,
       version      varchar2(20) not null ,
       updatedTime  date default sysdate
);
-- Add comments to the table
comment on table t_dictionary
  is '字典配置表';
-- Add comments to the columns
comment on column t_dictionary.field
  is '字段名称';
comment on column t_dictionary.dictValue
  is '字典实际值';
comment on column t_dictionary.dictDisplay
  is '字典显示值';
comment on column t_dictionary.version
  is '版本号';
comment on column t_dictionary.updatedTime
is '数据更新时间戳，默认系统时间';

-- 创建序列
CREATE SEQUENCE seq_dict START WITH 1;

-- Create table
create table T_SERVICE_CATALOG
(
  NAME     VARCHAR2(50),
  TITLE    VARCHAR2(50),
  ORDERNUM NUMBER
);
-- Add comments to the table
comment on table T_SERVICE_CATALOG
  is '事项配置表';
-- Add comments to the columns
comment on column T_SERVICE_CATALOG.NAME
  is '事项类别(代码)';
comment on column T_SERVICE_CATALOG.TITLE
  is '事项类别(标题)';
comment on column T_SERVICE_CATALOG.ORDERNUM
  is '排序';