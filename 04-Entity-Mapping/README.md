# Entity 매핑
- JPA를 사용하는데 가장 중요한 일은 엔티티와 테이블을 정확히 매핑하는것이다.
 JPA 매핑 어노테이션은 크게 4가지로 분류할수 있다
<br>
- 객체와 테이블 매핑 : @Entity, @Table
- 기본 키 매핑 : @Id
- 필드와 컬럼 매핑 : @Column
- 연관관계 매핑 : @ManyToOne, @JoinColumn
 <br> 
### @Entity
- 테이블과 매핑할 클래스는 @Entity 어노테이션을 붙여야 한다
- 이 어노테이션이 붙은 클래스는 JPA과 관리한다
> #### 주의사항
>- 기본 생성자는 필수(파라미터가 없는 public 또는 protected 생성자).
>- final 클래스, enum, interface,inner클래스에서는 사용이 불가능
>- 저장할 필드에 final을 사용하면 안된다.
 <br>
### @Table
- 엔티티와 매핑할 테이블을 지정 
- 생략시 매핑한 엔티티 이름을 테이블 이름으로 사용.

### DB스키마 자동생성
- jpa는 스키마를 자동으로 생성하는 기능을 지원한다.
- 매핑정보와 DB방언을 통해서 db스키마를 생성한다.
> persistence.xml에 속성추가
>  - property name="hibernate.hbm2ddl.auto" value="craet"
> - 해당 속성은 어플리케이션 실행 시점에 db 테이블으 자동으로 생성한다.
> #### hibernate.hbm2ddl.auto 속성
> - create : 기존테이블을 삭제하고 새로 생성 DROP + CRAET
> - create-drop : create 속성에 추가로 어플리케이션 종료시점에 ddl 제거 DROP+CRAET+DROP
> - update : 데이터베이스 테이블과 앤티티 매핑정보를 비교해서 변경 사항만 수정
> - validate : 데이터베이스 테이블과 매핑정보를 비교해서 차이가 있으면 경고를 남기고 애플리케이션을 실행하지 않음(ddl을 수정하지 않음)
> - none : 자동생성 기능을 사용하지 않음(속성자체를 삭제하거나 유효하지 않은 옵션값을 주면됨)
### DDL 생성 기능
- DDL 제약 조건 추가(회원이름은 필수로 입력되어야 하고 10자를 초과하면 안된다)
- @Column 매핑정보의 nullable 속성값을 false, length 속성 값을 사용하면 DDL문자의 크기를 지정가능 (not null, 10글자)
- 유니크 제약 조건
>  @Table의 uniqueConstraints 속성
> - uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"})})
> - 이 기능들은 단지 DDL을 자동으로 생성할 경우에만 사용되고 JPA 실행 로직에는 영향으주지 않는다
> - 해당기능을 사용하면 개발자가 Entity만 보고 손쉽게 다양한 제약 조건을 파악할 수 있는 장점이 있다.
### 기본키 매핑
- JPA가 제공하는 데이터베이스 기본 키 생성 전략
> - 직접 할당 : 기본키를 어플리케이션에서 직접 할당.
> - 자동생성 : 대리 키 사용 방식
>> - IDENTITY : 기본키 생성을 데이터베이스에 위임
>> - SEQUENCE : 데이터베이스 시퀀스를 사용해서 기본 키를 할당
>> - TABLE : 키 생성 테이블을 사용

#### 기본키 직접 할당 전략
- 직접 할당시 다음 코드와 같이 @Id로 매핑 하면됨
<pre><code>@Id
@Column(name = "id")
priavet String id;</code></pre>
- @Id 에 적용 가능한 자바타임
> - 자바 기본형
> - 자바 래퍼형
> - String
> - java.util.DATE
> - java.sql.Date
> - java.math.BigDecimal
> - java.math.BigInger
- 기본키 직접 할당 정략은 em.persist()로 엔티티를 저장하기전 애플리케이션에서 직접 기본키를 할당하는 방법이다.
<pre><code>Board board = new Board();
board.setId("id1") // 기본키 직접 할당
em.persist(board)</code></pre>

### 자동생성 - IDENTITY 전략
- 키본키 생성을 데이터베이스에 위임하는 전략 
- MySQL, PostgreSQL, SQL Server, DB2에서 사용
> - 예 : MySQL의 AUTO_INCREMENT 기능 DB가 자동으로 기본 키생성

