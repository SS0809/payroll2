# payroll2


## SQL 
```
create database payroll2;
create user 'payrolluser'@'%' identified by 'P@assword$3456';
grant all on payroll2.* to 'payrolluser'@'%';
```
```
SET PAYROLL_DB_USERNAME=payrolluser
SET PAYROLL_DB_PASSWORD=P@assword$3456
```

```
mvn clean verify sonar:sonar "-Dsonar.projectKey=payroll2" "-Dsonar.projectName=payroll2" "-Dsonar.host.url=http://localhost:9000" "-Dsonar.token="
```

```
StartSonar.bat
```