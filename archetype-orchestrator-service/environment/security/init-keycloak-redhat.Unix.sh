#!/usr/bin/env bash

KEYCLOAK_HOST_PORT=${1:-"localhost:8088"}
echo
echo "KEYCLOAK_HOST_PORT: $KEYCLOAK_HOST_PORT"
echo

echo "Getting admin access token"
echo "=========================="

ADMIN_TOKEN=$(curl -s -X POST "http://$KEYCLOAK_HOST_PORT/realms/master/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=admin" \
  -d 'password=admin' \
  -d 'grant_type=password' \
  -d 'client_id=admin-cli' | jq -r '.access_token')

echo "ADMIN_TOKEN=$ADMIN_TOKEN"
echo

echo "Creating realm"
echo "=============="

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"realm": "sorteo-services", "enabled": true}'

echo "Get Required Action Verify Profile"
echo "----------------------------------"

VERIFY_PROFILE_REQUIRED_ACTION=$(curl -s "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/authentication/required-actions/VERIFY_PROFILE" \
  -H "Authorization: Bearer $ADMIN_TOKEN" | jq)

echo $VERIFY_PROFILE_REQUIRED_ACTION
echo

echo "Disable Required Action Verify Profile"
echo "--------------------------------------"

NEW_VERIFY_PROFILE_REQUIRED_ACTION=$(echo "$VERIFY_PROFILE_REQUIRED_ACTION" | jq '.enabled = false')

echo $NEW_VERIFY_PROFILE_REQUIRED_ACTION
echo

curl -i -X PUT "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/authentication/required-actions/VERIFY_PROFILE" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d "$NEW_VERIFY_PROFILE_REQUIRED_ACTION"

echo "Creating client"
echo "==============="

CLIENT_ID=$(curl -si -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/clients" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"clientId": "ocp-nsp-sorteo-service", "directAccessGrantsEnabled": true, "redirectUris": ["http://localhost:9080/*"]}' \
  | grep -oE '[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}')

echo "CLIENT_ID=$CLIENT_ID"
echo

echo "Getting client secret"
echo "====================="

SERVICE_CLIENT_SECRET=$(curl -s -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/clients/$CLIENT_ID/client-secret" \
  -H "Authorization: Bearer $ADMIN_TOKEN" | jq -r '.value')

echo "SERVICE_CLIENT_SECRET=$SERVICE_CLIENT_SECRET"
echo

echo "Creating client role"
echo "===================="

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/clients/$CLIENT_ID/roles" \
-H "Authorization: Bearer $ADMIN_TOKEN" \
-H "Content-Type: application/json" \
-d '{"name": "IGT-USER"}'

ROLE_ID=$(curl -s "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/clients/$CLIENT_ID/roles" \
  -H "Authorization: Bearer $ADMIN_TOKEN" | jq -r '.[0].id')

echo "ROLE_ID=$ROLE_ID"
echo

echo "Configuring LDAP"
echo "================"

LDAP_ID=$(curl -si -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/components" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '@ldap/ldap-config.json' \
  | grep -oE '[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}')

echo "LDAP_ID=$LDAP_ID"
echo

echo "Sync LDAP Users"
echo "==============="

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/user-storage/$LDAP_ID/sync?action=triggerFullSync" \
  -H "Authorization: Bearer $ADMIN_TOKEN"


echo "Get sorteob id"
echo "============"

SORTEO_B_ID=$(curl -s "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/users?username=sorteob" \
  -H "Authorization: Bearer $ADMIN_TOKEN"  | jq -r '.[0].id')

echo "SORTEO_B_ID=$SORTEO_B_ID"
echo

echo "Setting client role to sorteob"
echo "============================"

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/users/$SORTEO_B_ID/role-mappings/clients/$CLIENT_ID" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '[{"id":"'"$ROLE_ID"'","name":"IGT-USER"}]' 

echo "Get anonymous id"
echo "============"

ANONYMOUS_ID=$(curl -s "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/users?username=anonymous" \
  -H "Authorization: Bearer $ADMIN_TOKEN"  | jq -r '.[0].id')

echo "ANONYMOUS_ID=$ANONYMOUS_ID"
echo

echo "Setting client role to anonymous"
echo "============================"

curl -i -X POST "http://$KEYCLOAK_HOST_PORT/admin/realms/sorteo-services/users/$ANONYMOUS_ID/role-mappings/clients/$CLIENT_ID" \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
  -H "Content-Type: application/json" \
  -d '[{"id":"'"$ROLE_ID"'","name":"IGT-USER"}]'


echo "Getting sorteob access token"
echo "=========================="

curl -s -X POST "http://$KEYCLOAK_HOST_PORT/realms/sorteo-services/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=sorteob" \
  -d "password=123" \
  -d "grant_type=password" \
  -d "client_secret=$SERVICE_CLIENT_SECRET" \
  -d "client_id=ocp-nsp-sorteo-service" | jq -r .access_token
echo

echo "Getting anonymous access token"
echo "=========================="

curl -s -X POST "http://$KEYCLOAK_HOST_PORT/realms/sorteo-services/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=anonymous" \
  -d "password=123" \
  -d "grant_type=password" \
  -d "client_secret=$SERVICE_CLIENT_SECRET" \
  -d "client_id=ocp-nsp-sorteo-service" | jq -r .access_token
echo

echo "============================"
echo "SERVICE_CLIENT_SECRET=$SERVICE_CLIENT_SECRET"
echo "============================"
