spool wx-server.log

set feedback off;
SET define ON;

prompt ϵͳ��ʼ����ʼ

prompt ����ʼ
@@create_tables.sql
prompt �������

prompt �������ÿ�ʼ
@@init_parameters.sql
prompt �����������

prompt ϵͳ��ʼ�����

commit;

spool off