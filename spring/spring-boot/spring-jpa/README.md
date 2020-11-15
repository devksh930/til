# SPRING - JPA
### ORM과 JPA
- 객체와 릴레이션을 맵핑할때 발생하는 개념적 불일치를 해결하는 프레임워크

### spring-data-jpa
- Repository 빈 자동 생성
- 쿼리 메소드 자동 구현
- @EnableJpaRepositories(스프링부트가 자동 설정)
- spring-data-jap -> JPA -> hibernate -> Datasource

### spring-data-jpa 사용하기
- @Entity 클래스 만들기
- Repository 만들기

### 스프링 데이터 repository 테스트 만들기
- h2 db를 테스트 의존성에 추가하기
- @DataJpaTest (슬라이스 test) 작성
> - @DataJpaTest가 아닌 @SpringBootTest를 하게 되면 메인애플리케이션을 찾아서 해당되는 빈을 전부 등록 하게되어 Postgresql을 사용하게된다
> - Test를 돌리기 위해선 임베디드 H2 DB를 사용 하는것이 훨씬 더 빠르고 좋을것 같다.

# DATABASE 초기화
### jpa를 사용환 초기화
- spring.jpa.hibernate.ddl-auto > 스키마 자동생성
> - update : 기존의 데이터를 놔두고 추가된것만 변경
> - create-drop :처음에 생성 애플리케이션 종료시 삭제 
> - create : 시작할때 지우고 다시만듬 
>> - 운영시 : validate, ddl=false
>> validate는 entity맵핑이 릴레이션 db에 맵핑을 할수 있는 상황인지 검증만 하는것 ddl의 변경을 하지는 않는다.
- spring.jpa.generate-dll=true

### SQL 스크립트를 통한 데이터베이스 초기화
- 순서는 schema.sql -> data.sql
- schema.sql 또는 schema-${platform}.sql
- data.sql 또는 schema-${platform).sql
- ${platform) 값은 spring.datasource.platform으로 설정가능

# DB-Migration
- Flyway, Liquibase가 대표적이다. Flyway사용

### 의존성
- org.flywaydb:flyway-core

### 마이그레이션 디렉토리
- db/migration/{vendor}
- spring.flyway.locations로 변경가능

### 마이그레이션 파일 이름
- V숫자__이름.sql
- V는 꼭 대문자
- 숫자는 순차적(timestamp 권장)
- 숫자와 이름 사이에 언더바 두개
- 이름은 가능한 서술적으로
