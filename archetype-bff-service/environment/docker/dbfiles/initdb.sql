alter session set "_ORACLE_SCRIPT"=true;  
CREATE USER oracleuserb  IDENTIFIED BY oraclepw;
grant connect, resource to oracleuserb;
grant unlimited tablespace to oracleuserb;
grant create synonym, create public synonym to oracleuserb;
grant create view to oracleuserb;
grant create session to oracleuserb;