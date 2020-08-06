# SPRING DATA MYSQL(mariaDB)
### 지원하는 DBCP
- HikariCP(기본)
> -  https://github.com/brettwooldridge/HikariCP#frequently-used
- Tomcat CP
- Commons DBCP2

### DBCP 설정
- spring.datasource.hikari.*
- spring.datasource.tomcat.*
- spring.datasource.dbcp2.*

### PROPERTY 설정
- spring.datasource.url=jdbc:mysql://localhost:3306/springboot?useSSL=false
- spring.datasource.username=root
- spring.datasource.password=test
### mysql,mari 사용시 주의점
- MySQL 라이센스 (GPL) 주의
- MySQL 대신 MariaDB 사용 검토 
- 소스코드공개의무여부확인

