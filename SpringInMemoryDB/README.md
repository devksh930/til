# 스프링 인메모리 데이터베이스

### 지원하는 in-memory 데이터베이스
- <b>H2</b>
- HSQL
- Derby

### SPRING-JDBC 가 클래스패스에 있으면 자동 설정이 필요한 빈을 설정 해줌
- DataSource
- JdbcTemplate
> - JdbcTemplate를 사용하면 좀더 안전하게 쿼리를 던질수 있다. 리소스반납처리나 예외를 던질때 좀더 가독성이 높은 에러를 확인 할수 있다.

### In-Memory 데이터베이스 기본 연결 정보 확인하는 방법
- URL:"testdb"
- username: "sa"
- password: ""

### H2 Console 사용법
- spring-boot-devtools 추가(이방법은 여러가지 기본 propertie들도 바뀐)
- spring.h2.console.enabled=true 추가
- /h2-console로 접속(이 path도 바꿀수 있다)
- spring-boot 2.3 이상에서는 spring.datasource.url=jdbc:h2:mem:testdb 추가
