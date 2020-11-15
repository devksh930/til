# JPA 시작
### 의존성 추가
> #### pom.xml
> - hibernate-entitymanager : JPA표주과 하이버네이트를 포함
>> - hibernate-core.jar
>> - hibernate-jpa-2.1-api.jar 
> - h2 : 임베디드 경량 DB
### JPA 매핑
- JPA를 사용 하려면 클래스와 테이블을 매핑 해야 한다.
> #### 어노테이션
> - @Entity : class를 table과 매핑 한다고 jpa에 알려줌
> - @Table : 엔팉티 클레스에 매핑할 table정보를 알려줌
> - @Id : 엔티티클래스의 필드를 PK에 매핑(식별자 필드)
> - @Colulmn : 필드를 컬럼에 매핑 
> - 매핑정보 없는 필드 : 매핑 어노테이션을 생략하면 필드명을 사용하여 컬럼명으로 매핑
### persistence.xml 설정
- JPA는 persistence.xml을 이용하여 설정을 관리한다. 
- 경로는 META-INF/persistence.xml 클래스 패스 경로에 있으면 별도의 설정 없이 JPA가 인식가능
> ####  JPA 표준 속성
> - javax.persistence.jdbc.driver : JDBC 드라이버
> - javax.persistence.jdbc.user : DB접속 id
> - javax.persistence.jdbc.password : DB접속 pw
> - javax.persistence.jdbc.url : DB접속 URL
>#### 하이버 네이트 속성
> - hibernate.dialect : db 방언 설정
>> DB 방언
>> - jpa는 특정 데이터베이스에 종속적이지 않다. 다른 데이터베이스로 손쉽게 교체가 가능하다. SQL표준을 지키지 않거나 특정 데이터베이스만의 고유한 기능을 JPA에서는 Dialect(방언)이라고 한다.
### 엔티티 메니저
- 엔티티 매니저 팩토리 생성
> - persistence.xml의 정보를 일거 jpa를 동작 시키기 위한 기반 객체를 만들고 jpa구현체에 따라서는 데이터베이스 커넥션 풀도 생성한다. 그래서 엔티티 매니저팩토리를 생성하는 비용은 아주 크다 <b>엔티티 매니저 팩토리는 애플리케이션 전체에서 딱 한 번만 생성하고 공유해서 사용해야한다.</b>
- 엔티티 매니저 생성
> - 엔티티 매니저 팩토리에서 앤티티 메니저를 생성한다. jpa기능의 대부분은 엔티티 매니저가 제공한다. <b>엔티티 매니저는 데이터베이스 커넥션과 밀접한 관계가 있으므로 스레드간 공유하거나 재사용 하면 안된다.</b>