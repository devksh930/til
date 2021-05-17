# 객체지향 쿼리언어

## 소개

`EntityManager.find()` 메서드를 사용하면 식별자로 엔티티 하나를 조회할 수 있다. 이렇게 조회한 엔티티 객체 그래프 탐색을 사용하면 연관된 엔티티들을 찾을수 있다.

- 식별자로 조회 `EntityManage.find()`
- 객체 그래프 탐색(예: `a.geB().getC()`)

하지만 이 기능만으로는 애플리케이션을 개발하기 힘들다. 예를 들어 나이가 30살 이상인 회원을 검색하고 싶다면 좀더 현실적이고 복잡한 방법을 필요로한다. 그렇다고 메모리에 회원엔티티를 모두 올려두고 애플리케이션에서
30살이상인 회원을 검색하는것은 현실성이 없다. 데이터는 DB에 있으므로 SQL로 필요한 내용을 최대한 걸러 조회해야 한다.

ORM을 사용하게 되면 데이터베이스 테이블이 아닌 엔티티 객체를 대상으로 개발한다. 검색도 테이블이 아닌 엔티티 객체를 대상으로 하는 방법이 필요하다

JPQL은 이러한 문제를 해결하기 위해 만들어졌다.

- 테이블이 아닌 객체를 대상으로 검색하는 객체지향쿼리 이다.
- SQL 추상화 해서 특정 DB SQL에 의존하지 않는다.

JPA가 공식으로 지원하는 기능

- JPQL(Java Persistence Query Language)
- Creteria 쿼리 : JPQL을 편하게 작성하도록 도와주는 API, 빌더클래스 모음
- 네이티브 SQL : JPA에서 JPQL 대신 직접 SQL을 사용할 수 있다.

JPA가 공식 지원하는 기능은 아니지만 알아둘 필요가 있는 기능

- QueryDSL : Criteria 쿼리처럼 JPQL을 편하게 작성하도록 도와주는 빌더 클래스 모음(비표준 오픈소스 프레임워크)
- JDBC 직접사용, MyBatis 같은 SQL 매퍼 프레임워크 사용: 필요하면 JDBC를 직접 사용가능하다.

## JPQL 소개

- JPQL은 엔티티 객체를 조회하는 객체지향 쿼리다.
    - 문법은 SQL과 비슷하고 ANSI 표준 SQL이 제공하는 기능을 유사하게 지원한다
- JQL은 SQL을 추상화 해서 특정 데이터베이스에 의존하지 않는다.
    - 데이터베이스 방언(Dialect)만 변경하면 JPQL을 수정하지 않아도 자연스럽게 DB변경 가능
    - SQL 함수라도 JPQL이 제공하는 표준함수를 사용하면 선택한 방언에 따라 해당 DB에 맞는 적절한 SQL함수 생성

```java
        String jpql="select m from Member as m where m.username = 'kim' ";
        List<Member> resultList=em.createQuery(jpql,Member.class).getResultList();
```

- 회원 이름이 kim 인 엔티티를 조회한다. `JPQL`에서 `Member`는 엔티티 이름이다.
- `m.username`는 테이블 컬럼명이 아닌 엔티티 객체의 필드명이다.
- `em.createQuery()`메서드에 실행할 JPQL과 반환할 엔티티 클래스 타입인 `Member.class`를 넘겨주고 `getResultList()`메서드 실행시 JPA는 JPQL을 SQL로 변환해서
  DB를 조회한다.

실제 실행된 SQL

```SQL
    /* select
        m 
    from
        Member as m 
    where
        m.username = 'kim'  */
select member0_.name as name1_0_
from Member member0_
where member0_.name = 'kim'
```

## Criteria 쿼리 소개

- `Criteria`는 JPQL을 생성하는 빌더 클래스다.
- 문자가 아닌 `query.select(m).where(...)`처럼 프로그램 코드로 `JPQL`을 작성할 수 있다.

> JPQL에서 select m form Membeee m 처럼 오타가 있으면 컴파일은 성공하며 애플리케이션서버에 배포가 가능하다.
> 하지만 해당 쿼리가 실행되는 런타임 시점에 오류가 발생한다.
> `반면 Criteria는 문자가 아닌 코드로 JPQL을 작성한다. 따라서 컴파일 시점에 오류 발견이 가능하다`

Criteria의 장점

- 컴파일 시점에 오류를 발견할 수 있다.
- IDE사용시 코드 자동완성을 지원한다.
- 동적 쿼리를 작성하기 편하다.

`select m from Member as m where m.username = 'kim'` 를 `Criteria`로 변경

```java
    public static void findCriteria(EntityManager em){
        //Criteria 사용준비
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Member> query=cb.createQuery(Member.class);

        //루트 클래스(조회를 시작할 클래스)
        Root<Member> m=query.from(Member.class);

        //쿼리생성
        CriteriaQuery<Member> cq=query.select(m).where(cb.equal(m.get("username"),"kim"));
        List<Member> resultList=em.createQuery(cq).getResultList();
        for(Member member:resultList){
        System.out.println(member.getUsername());
        }
```

- `Criteria`는 코드로 쿼리를 작성할 수 있어서 동적 쿼리를 작성할때 유용하다

`Criteria가 가진 장점은 많다 하지만 복작하고 장황해 사용하기 불편하고 Criteria로 작성한 코드는 한눈에 들어오지 않는다`

## QueryDSL 소개

`QueryDSL`도 `Criteria`처럼 JPQL 빌더 역할을 한다. `Criteria`에 비해 단순하고 사용하기도 쉽다.
> - 참고
>
> QueryDSL은 JPA표준이 아니라 오픈소스 프로젝트이다. JPA뿐 아니라 JDO, 몽고DB, Java Collection, Lucene, Hibernate Search도 거의 같은뭄버으로 지원한다.

```java
//준비
JPAQuery query=new JPAQuery(em);
        QMember member=QMember.member;

//쿼리, 조회결과
        List<Member> members=
        query.from(member)
        .where(member.username.eq("kim"))
        .list(member);
```

코드를 보게 되면 쉽게 작성이 가능하다. QueryDSL도 어노테이션 프로세서를 사용해서 쿼리 전용 클래스를 만들어야 한다.
(QMember는 Member 엔티티 클래스를 기반으로 생성한 `QueryDSL`쿼리전용 클래스 이다)

## 네이티브 SQL 소개

- JPA에서 SQL을 직접 사용할 수 있는 기능이다.
- JPQL을 사용하더라도 특정 데이터베이스에 의존하는 기능을 사용해야 할때가 있다.
    - 오라클 DB만 사용하는 `CONNECT BY`나 특정 DB에서 동작하는 SQL 힌트 같은것
- 특정 데이터베이스에 의존하는 SQL을 작성해야한다. 따라서 DB변경시 네이티브 SQL도 수정해야한다.
- 네이티브 SQL은 `em.createNativeQuery()`를 사용하면도니다.

## JDBC 직접사용, 마이바티스 같은 SQL 매퍼 프레임워크 사용

JPA는 JDBC 커넥션을 획득하는 API를 제공하지 않는다. JPA 구현체가 제공하는 방법을 사용해야한다.

하이버네이트에서 직접 JDBC Connection을 획득하는 방법

```java
Session session=entityManager.unwrap(Session.class);
        session.doWork(newWork()){
            
    @Override
    public void execute(Connection connection)throws SQLException{
    //work    
        }
        }
```
- JDBC나 마이바티스를 JPA와 함께 사용하면 영속성 컨텍스트를 적절한 시점에 강제로 플러시 행햐ㅏㄴ다.
- JDBC를 직접사용하던지 마이바티스 같은 SQL매퍼를 쓰게되면 JPA를 우회해서 데이터베이스에 접근한다.
    - JPA를 우회하는 SQL에 대해서는 JPA가 전혀 인식하지 못한다
- 이런 이슈는 JPA를 우회해서 실행하기전 영속성 컨텍스트를 수동으로 플러시해서 영속성 컨텍스트를 동기화하면된다.
- 스프링프레임워크의 AOP를 활용하면 해당 문제도 해결이 가능하다.

## JPQL

JPQL의 특징

- JPQL은 객체지향 쿼리 언어이다. 테이블 대상 쿼리가 아니라 엔티티 대상 쿼리이다.
- JPQL은 SQL을 추상화해서 특정 데이터베이스 SQL에 의존하지 않는다.
- JPQL은 결국 SQL로 변환된다.

### 기본 문법과 쿼리 API
- JPQL도 SQL과 비슷하게 `SELECT`,`UPDATE`,`DELETE`문을 사용할수 있다.
- 엔티티 저장시에는 `EntityManager.persist()` 메서드를 사용하면 되므로 `INSERT`문은 없다

### SELECT 문

`SELECT m FROME Member AS m where m.username = 'Hello'`

- 대소문자 구분
    - 엔티티의 속성은 대소문자를 구분한다.(예: Member, username)
    - 반면 `SELECT`,`FROM`,`AS`같은 JPQL 키워드는 대소문자를 구분하지 않는다
- 엔티티 이름
    - JPQL에서 사용한 Member는 클래스 명이 아니라 엔티티 명이다.(엔티티명은 `@Entity(name = "xxxx")로 지정가능)
    - 엔티티명 미지정시 클래스 명을 기본값으로 사용
- 별칭은 필수
    -`Member as m`을 보게되면 `Member`에 `m`이라는 별칭을 주었따.
    - 별칭없이 작성하게 되면 잘못된 문법이라는 오류가 발생한다.
    > `SELECT username FROM Member m` 해당 문법은 `username`을 `m.username`으로 고쳐야한다

### TypeQuery, Query
작성한 JPQL을 실행하려면 쿼리객체를 만들어야 하는데 쿼리 객체는 `TypeQuery`와 `Query`가 있다.

- ` TypeQuery` : 반환할 타입을 명확하게 지정할수 있다.
- `Query` : 반환타입을 명확하게 지정할수 없다.

- TypeQuery 사용
```java
    public static void TypeQuery(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("SELECT m from Member m", Member.class);

        List<Member> resultList = query.getResultList();
        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }
```
- `em.createQuery()` 두번째 파라미터에 반환할 타입을 지정하면 `TypeQuery`를 반환하고 지정하지 않으면 `Query`를 반환한다
- 조회대상이 Member 엔티티 이므로 조회대상 타입이 명확하다.

- Query 사용
```java
    public static void query(EntityManager em) {
        Query query = em.createQuery("SELECT m.username, m.age from Member m");

        List resultList = query.getResultList();

        for (Object o : resultList) {
            Object[] result = (Object[]) o;
            System.out.println("username " + result[0]);
            System.out.println("age " + result[1]);

        }
```
- 조회대상이 `String`타입과 `Integer`타입 이므로 조회대상이 명확하지 않다.
- Select 절에서 여러 엔티티나 컬럼을 선택할때는 반환할 타입이 명확하지 않으므로 Query 객체를 사용해야한다.


 결과조회
- `query.getResultList()` : 결과를 List로 반환한다 만약 결과가 없으면 빈컬렉션을 반환한다
- `query.getSingleReuslt()` : 결과가 장확히 하나일 때 사용한다.
    - 결과가 없으면 `javx.persistence.NoResultException` 예외발생
    - 결과가 1개보다 많으면 `javax.persistence.NonUniqueResultException`예외발생
  
### 파라미터 바인딩
JDBC는 위치 기준 파라미터 바인딩만 지원한다. 하지만 JPQL은 이름 기준 파라미터 바인딩도 지원한다.

- 이름 기준 파라미터
    - 이름 기준 파라미터(`Named parametes`)는 파라미터를 이름으로 구분하는 방법이다.
    - 이름 기준 파라미터는 앞에 :를 사용한다
 ```java
    public static void namedParameter(EntityManager em){
        String usernameParam="kim";

        TypedQuery<Member> query = em.createQuery(
                "SELECT  m FROM Member m WHERE m.username =:username", Member.class);

        query.setParameter("username", usernameParam);
        List<Member> resultList = query.getResultList();
}
``` 
- `:username`이라는 이름 기준 파라미터를 정의하고 `query.setParameter()`에서 파라미터를 바인딩한다
- JPQL API는 대부분 메서드 체인 방식으로 설계되어 있어서 다음과 같이 작성도 가능하다.
```java
        List<Member> resultList = em.createQuery(
                "SELECT m FROM Member m WHERE m.username =:username", Member.class)
                .setParameter("username", usernameParam)
                .getResultList();
```

- 위치기준 파라미터
위치기준 파라미터 `Positional parameters`를 `?`다음 위치 값을 주면 된다.
```java
        String usernameParam = "kim";

        List<Member> resultList = em.createQuery("SELECT m FROM Member m where m.username =?1", Member.class)
                .setParameter(1, usernameParam)
                .getResultList();
```
- 위치 기준 파라미터 방식보다 `이름 기준 파라미터 바인딩 방식을 사용하는 것이 더 명확하다.`

>참고
> - JPQL사용시 파라미터 바인딩 방식을 사용하지 않고 직접 문자를 더해 만들어 넣으면 악의적인 사용자에 의해서 SQL인젝션 공격격의 위험성이 존재하며 성능이슈도 존재한다.
> - `파라미터 바인딩 방식은 선택이 아닌 필수이다.`
> 