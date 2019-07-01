# How to deploy on Cloud Foundry

```
./mvnw clean package -DskipTests=true

# Create a mysql service
# for PWS
cf create-service cleardb spark mrs-db
# or for MySQL for PCF
cf create-service p.mysql db-small mrs-db

cf push
```