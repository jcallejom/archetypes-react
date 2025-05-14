#!/bin/bash

set -e

BASEDIR=$(dirname "$0")
LDAP_HOST=${1:-localhost:389}

initEnvironment(){
  printf "\nStarting environment...\n"
  docker compose -f "$BASEDIR"/docker/docker-compose.yml up -d
}

initTelemetry(){
  printf "\nInit telemetry...\n"
  docker compose -f "$BASEDIR"/docker/docker-compose-ottel.yml up -d
}
initKafka(){
  printf "\nInit kafka infraestructure...\n"
  docker compose -f "$BASEDIR"/docker/docker-compose-kafka-debz.yml up -d
  #docker compose -f "$BASEDIR"/docker/docker-compose-kafka-n.yml up -d
}
registerLdap(){
  printf "\nRegistering Ldap conection...\n"
  sleep 10
  #ldapadd -x -D "cn=admin,dc=maincompany,dc=com" -w admin -H ldap://$LDAP_HOST -f security/ldap/ldap-company-com.ldif
  sh "$BASEDIR"/security/import-openldap-users.sh
}

registerKeycloack(){
  printf "\nRegistering Keycloack conection...\n"
  sleep 40
  #sh "$BASEDIR"/security/init-keycloak-bitamini.sh
  #sh "$BASEDIR"/security/init-keycloak-redhat.sh
  sh "$BASEDIR"/init-keycloak-bitamini-ldap.sh
}
registerConnector(){
  printf "\nRegistering Debezium connector...\n"
  sleep 20
  curl -s -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d @"$BASEDIR"/debezium/connector-oracle.json > /dev/null
}

initEnvironment
initTelemetry
initKafka
registerLdap
registerKeycloack
#registerConnector