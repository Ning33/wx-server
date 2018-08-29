spool wx-server.log

set feedback off;
SET define ON;

prompt 系统初始化开始

prompt 建表开始
@@create_tables.sql
prompt 建表完成

prompt 参数配置开始
@@init_parameters.sql
prompt 参数配置完成

prompt 系统初始化完成

commit;

spool off