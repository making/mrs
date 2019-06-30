# How to deploy on Cloud Foundry

```
cf push -i 0
# Initialize DB
cf run-task mrs ".java-buildpack/open_jdk_jre/bin/java org.springframework.boot.loader.JarLauncher --spring.main.web-application-type=none --spring.datasource.initialization-mode=always --logging.level.org.springframework.jdbc=DEBUG"
cf scale mrs -i 1
```