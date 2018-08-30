-- 事项配置
insert into t_service (SERVICEID, NAME, TITLE, DESCRIPTION, CATALOG, SECURITYLEVEL, ORDERNUM)
values (sys_guid(), 'yldyhd', '养老待遇核定', '办理离退休待遇', 'yldy', '3', null);

insert into t_service (SERVICEID, NAME, TITLE, DESCRIPTION, CATALOG, SECURITYLEVEL, ORDERNUM)
values (sys_guid(), 'cbjfxx', '参保缴费信息', '缴费信息查询', 'bxgx', '2', null);

insert into t_service (SERVICEID, NAME, TITLE, DESCRIPTION, CATALOG, SECURITYLEVEL, ORDERNUM)
values (sys_guid(), 'txdysl', '退休待遇申领', '领取退休金', 'yldy', '3', null);

insert into t_dictionary(field , dictvalue , dictdisplay , version )
values('性别','1','男',seq_dict.nextval);

insert into t_dictionary(field , dictvalue , dictdisplay , version )
values('性别','2','女',seq_dict.nextval);

insert into t_service_catalog(name , title , ordernum)
values('bxgx','征缴业务',1);

insert into t_service_catalog(name , title , ordernum)
values('yldy','养老待遇',2);

insert into t_service_catalog(name , title , ordernum)
values('gsdy','工伤待遇',3);

insert into t_service_catalog(name , title , ordernum)
values('syedy','失业待遇',4);

insert into t_service_catalog(name , title , ordernum)
values('yildy','医疗待遇',5);