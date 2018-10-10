create table t_validate_face_log(
   token    varchar2(50),
   idcard   varchar2(20),
   name     varchar2(50),
   data     clob,
   logTime date default sysdate
);
comment on table t_validate_face_log is '����ʶ����־';
comment on column t_validate_face_log.token is '��ȡ��Ϣƾ֤';
comment on column t_validate_face_log.idcard is '���֤��';
comment on column t_validate_face_log.name is '����';
comment on column t_validate_face_log.data is '��ϸ����';
comment on column t_validate_face_log.logTime is '��־��¼ʱ���';

--********************����ʶ����־��ϸ��Ϣs*********************--
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
comment on table t_validate_face_log_detail is '����ʶ����ϸ��־';
comment on column t_validate_face_log_detail.token is '��ȡ��Ϣƾ֤';
comment on column t_validate_face_log_detail.idcard is '���֤��';
comment on column t_validate_face_log_detail.name is '����';
comment on column t_validate_face_log_detail.data is '��ϸ����';
comment on column t_validate_face_log_detail.exist is '�Ƿ��Ѿ�������ϸ��־, 1 :��ʾ�Ѵ� ,2 :��ʾû�� ';
comment on column t_validate_face_log_detail.pic_1 is '��Ƭ���ݵ�ַ_1';
comment on column t_validate_face_log_detail.pic_2 is '��Ƭ���ݵ�ַ_2';
comment on column t_validate_face_log_detail.pic_3 is '��Ƭ���ݵ�ַ_3';
comment on column t_validate_face_log_detail.video is '��Ƶ���ݵ�ַ';
comment on column t_validate_face_log_detail.machine is '����Ӧ�ã���¼������һ��Ӧ�ô���';
comment on column t_validate_face_log_detail.logTime is '��־��¼ʱ���';

create table t_user(
   userId   varchar2(50) primary key,
   name     varchar2(50),
   idcard   varchar2(20),
   openid   varchar2(50),
   updatedTime date default sysdate
);
alter table t_user add constraint u_user_openid unique(openid);
comment on table t_user is '�û���Ϣ';
comment on column t_user.userId is '�û�ID';
comment on column t_user.name is '����';
comment on column t_user.idcard is '�������֤��';
comment on column t_user.openid is '΢���û�ID';
comment on column t_user.updatedTime is '���ݸ���ʱ���';


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
comment on table t_person is '��Ա��Ϣ��';
comment on column t_person.personId is '��ԱID';
comment on column t_person.userId is '�û�ID';
comment on column t_person.name is '����';
comment on column t_person.idcard is '�������֤����';
comment on column t_person.birthday is '��������';
comment on column t_person.sex is '�Ա�,1��,2Ů,9δ֪';
comment on column t_person.tel is '��ϵ�绰';
comment on column t_person.address is '��ϵ��ַ';
comment on column t_person.sicard is '�籣����';
comment on column t_person.updatedTime is '���ݸ���ʱ���';

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
comment on table t_order is '������Ϣ';
comment on column t_order.orderNo is '������';
comment on column t_order.serviceId is '��������ID';
comment on column t_order.serviceName is '������������';
comment on column t_order.userId is '�û�id';
comment on column t_order.userIdcard is '�û��������֤��';
comment on column t_order.userName is '�û�����';
comment on column t_order.personId is '�α���ID';
comment on column t_order.personIdcard is '�α��˹������֤����';
comment on column t_order.personName is '�α�������';
comment on column t_order.status is 'ҵ��״̬��0�걨�У�10����У�21��˳ɹ�,22���ʧ��';
comment on column t_order.requestData is 'ҵ��������Ϣ��json��ʽ�洢';
comment on column t_order.responseData is 'ҵ����Ӧ��Ϣ��json��ʽ�洢';
comment on column t_order.createdTime is '����ʱ��';
comment on column t_order.updatedTime is '����ʱ��';
comment on column t_order.completionTime is '���ʱ��';

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
comment on table t_service is '�������ñ�';
comment on column t_service.serviceId is '����ID';
comment on column t_service.name is '��������';
comment on column t_service.title is '�������';
comment on column t_service.description is '�����Ҫ';
comment on column t_service.catalog is '�������: bxgx-����ҵ��,yldy-���ϴ���,gsdy-���˴���,syedy-ʧҵ����,yildy-ҽ�ƴ���';
comment on column t_service.securityLevel is '��ȫ����';
comment on column t_service.orderNum is '��������';

create table t_dictionary(
       field      varchar2(20)  not null ,
       dictValue      varchar2(20) not null ,
       dictDisplay      varchar2(20) not null ,
       version      varchar2(20) not null ,
       updatedTime  date default sysdate
);
-- Add comments to the table
comment on table t_dictionary
  is '�ֵ����ñ�';
-- Add comments to the columns
comment on column t_dictionary.field
  is '�ֶ�����';
comment on column t_dictionary.dictValue
  is '�ֵ�ʵ��ֵ';
comment on column t_dictionary.dictDisplay
  is '�ֵ���ʾֵ';
comment on column t_dictionary.version
  is '�汾��';
comment on column t_dictionary.updatedTime
is '���ݸ���ʱ�����Ĭ��ϵͳʱ��';

-- ��������
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
  is '�������ñ�';
-- Add comments to the columns
comment on column T_SERVICE_CATALOG.NAME
  is '�������(����)';
comment on column T_SERVICE_CATALOG.TITLE
  is '�������(����)';
comment on column T_SERVICE_CATALOG.ORDERNUM
  is '����';