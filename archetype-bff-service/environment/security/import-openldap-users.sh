#!/usr/bin/env bash
BASEDIR=$(dirname "$0")
LDAP_HOST=${1:-localhost:389}

ldapadd -x -D "cn=admin,dc=maincompany,dc=com" -w admin -H ldap://$LDAP_HOST -f "$BASEDIR"/ldap/ldap-company-com.ldif