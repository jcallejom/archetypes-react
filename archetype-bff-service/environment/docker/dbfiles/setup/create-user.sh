#!/bin/sh

# Set archive log mode and enable GG replication
ORACLE_SID=XE
export ORACLE_SID
sqlplus /nolog <<- EOF
	CONNECT sys/oraclepw@XEPDB1 as sysdba;
	CREATE USER oracleuser IDENTIFIED BY oraclepw;
    GRANT CONNECT TO oracleuser;
    GRANT CREATE SESSION TO oracleuser;
    GRANT CREATE TABLE TO oracleuser;
    GRANT CREATE SEQUENCE to oracleuser;
    ALTER USER oracleuser QUOTA 100M on users;
    exit;
EOF
sqlplus sys/oraclepw@//localhost:1521/XEPDB1 as sysdba <<- EOF
  CREATE USER oracleuser IDENTIFIED BY oraclepw;
  GRANT CONNECT TO oracleuser;
  GRANT CREATE SESSION TO oracleuser;
  GRANT CREATE TABLE TO oracleuser;
  GRANT CREATE SEQUENCE to oracleuser;
  ALTER USER oracleuser QUOTA 100M on users;
  exit;
EOF