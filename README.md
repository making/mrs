# How to deploy on Cloud Foundry

```
# Create a mysql service
# for PWS
cf create-service cleardb spark mrs-db
# or for MySQL for PCF
cf create-service p.mysql db-small mrs-db

# Staging only first
cf push -i 0
# Initialize DB
cf run-task mrs ".java-buildpack/open_jdk_jre/bin/java org.springframework.boot.loader.JarLauncher --spring.main.web-application-type=none --spring.datasource.initialization-mode=always --logging.level.org.springframework.jdbc=DEBUG"
# Run mrs
cf scale mrs -i 1
```