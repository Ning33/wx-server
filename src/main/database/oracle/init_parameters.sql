-- ��������
insert into t_service (SERVICEID, NAME, TITLE, DESCRIPTION, CATALOG, SECURITYLEVEL, ORDERNUM)
values (sys_guid(), 'yldyhd', '���ϴ����˶�', '���������ݴ���', 'yldy', '3', null);

insert into t_service (SERVICEID, NAME, TITLE, DESCRIPTION, CATALOG, SECURITYLEVEL, ORDERNUM)
values (sys_guid(), 'cbjfxx', '�α��ɷ���Ϣ', '�ɷ���Ϣ��ѯ', 'bxgx', '2', null);

insert into t_service (SERVICEID, NAME, TITLE, DESCRIPTION, CATALOG, SECURITYLEVEL, ORDERNUM)
values (sys_guid(), 'txdysl', '���ݴ�������', '��ȡ���ݽ�', 'yldy', '3', null);

insert into t_dictionary(field , dictvalue , dictdisplay , version )
values('�Ա�','1','��',seq_dict.nextval);

insert into t_dictionary(field , dictvalue , dictdisplay , version )
values('�Ա�','2','Ů',seq_dict.nextval);

insert into t_service_catalog(name , title , ordernum)
values('bxgx','����ҵ��',1);

insert into t_service_catalog(name , title , ordernum)
values('yldy','���ϴ���',2);

insert into t_service_catalog(name , title , ordernum)
values('gsdy','���˴���',3);

insert into t_service_catalog(name , title , ordernum)
values('syedy','ʧҵ����',4);

insert into t_service_catalog(name , title , ordernum)
values('yildy','ҽ�ƴ���',5);