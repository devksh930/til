# 프록시와 연관관계 관리

- 프록시와 즉시로딩, 지연로딩 : JPA는 프록시라는 기술을 이용하여 처음부터 연관된 객체를 DB에서 조회하는게 아니라 실제 사용하는 시점에 db에서 조회 할수 있다.
- 영속성 전이와 고아 객체 : JPA는 연관된 객체를 함께 저장하거나 함께 삭제할수 있는 영속성 전이와 고아 객체 제거라는 편리한 기능을 제공한다

## 프록시

엔티티를 조회할 때 연관된 엔티티들이 항상 사용되는것이 아닌 회원 엔티티를 조회할때 연관된 팀 엔티티는 비즈니스 로직에 의해 사용될 경우도 있고 아닌경우도 있다.

```java
    public static void printUserAndTeam(EntityManager em){
        Member member=em.find(Member.class,"회원1");
        Team team=member.getTeam();
        System.out.println("회원 이름: "+member.getUsername());
        System.out.println("소속팀 : "+team.getName());
        }
public static void printUser(EntityManager em){
        Member member=em.find(Member.class,"회원1");
        System.out.println("회원 이름: "+member.getUsername());
        }
```

첫번째 코드의 `printUserAndTeam()`메서드는 회원의 이름으로 `Member`엔티티를 조회하고 연관된 `Team`엔티티도 조회한다. 반면 `printUser()`메서드는 `Member`엔티티만 조회하므로
연관된 `Team`엔티티까지 함께 DB에서 조회하는 것은 효율적이지 못하다. JPA에서는 이런 문제를 해결하기위해 DB에서 조회를 지연하는 방법을 제공한다. 이것이 `지연로딩`이다. `team.getName()`처럼
팀 엔티티의 값을 실제로 사용하는 시점에 DB에서 조회 하게 된다.
`이 지연 로딩 기능을 사용하려면 실제 엔티티 객체 대신 데이터베이스의 조회를 지연할수 있는 가짜 객체가 필요한데 이 가짜 객체가 프록시 객체라고한다.`

### 프록시 기초

JPA에서 식별자로 엔티티 하나를 조회할 때는 `EntityManager.find()`를 사용한다.

```java
Member member=em.find(Member.class,"member1");
```

이렇게 엔티티를 직접 조회시 엔티티를 실제로 사용하든 사용하지 않든간에 DB를 조회한다. 엔티티를 실제 사용하는 시점 까지 DB조회를 미루고 싶을경우 `EntityManager.gerReference()`메서드를
이욯마ㅕㄴ된다

```java
Member member=em.getReference(Member.class,"member1");
```

이 메서드를 호출할때 JPA는 DB를 조회하지 않고 실제 엔티티 객체도 생성하지 않는다. 대신 DB접근을 위임한 프록시 객체를 반환한다.

#### 프록시의 특징

프록시 클래스는 실제 클래스를 상속 받아 만들어진다. 실제 클래스와는 겉 모양이 같다.

![img.png](img.png)

- 사용하는 입장에서는 이것이 진짜 객체인지 프록시 객체인지 구분하지 않아도 된다.
- 프록시 객체는 실제 객체에 대한 참조(target)을 보관한다.
- 프록시 객체의 메서드를 호출하게되면 프록시 객체는 실제 객체의 메서드를 호출한다.
  ![img_1.png](img_1.png)

#### 프록시 객체의 초기화

프록시 객체는 `member.getName()`처럼 실제 사용될 때 DB를 조회해 실제 엔티티 객체를 생성한다. 이를 프록시 객체의 초기화라 한다.

- 프록시 예제

```java
//MemberProxy반환
Member meber=em.getReference(Member.class ,"id1");
        member.getName(); //1. getName
```

- 프록시 클래스 예상코드

```java
class MemberProxt extends Member {
    Member target = null;

    public String getName() {
        if (target == null) {
            //2. 초기화요청
            //3. DB조회
            //4. 실제 엔티티 생성 및 참조 보관
            this.target = ...;
        }
        //5. target.getName();
        return target.getName();
    }
}
```

1. 프록시 객체에 `member.getName()`을 호출, 실제 데이터 조회
2. 프록시 객체는 실제 엔티티가 생성되어 있지 않으면 영속성 컨텍스트에 실제 엔티티 생성을 요청한다(초기화)
3. 영속성 컨텍스트는 DB를 조회해 실제 엔티티 객체를 생성
4. 프록시 객체는 생성된 실제 엔티티 객체의 참조를 Member target 멤버 변수에 보관한다.
5. 프록시 객체는 실제 엔티티 객체의 `getName()`을 호출해서 결과를 반환한다.

#### 프록시의 특징

프록시의 특징은 다음과 같다

- 프록시 객체는 처음 사용할 때 한번만 초기화된다.
- 프록시 객체를 초기화한다고 프록시 객체가 실제 엔티티로 바뀌는것이 아니라 프록시를 통해 실제 엔티티에 접근이 가능하다.
- 프록시객체는 원본 엔티티를 상속받은 객체이므로 타입체크시 주의가 필요하다.
- 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 DB를 조회할 필요가 없다. `em.getReference()`를 호출해도 프록시가 아닌 실제 엔티티를 반환한다.
- 초기화는 영속성 컨텍스트의 도움을 받아야만 가능하다. 준영속 상태의 프록시를 초기화하면 문제가 발생한다.

#### 준영속 상태와 초기화

```java
//MemberProxy 반환
Member member=em.getReference(Member.class,"id1");
        transaction.commit();
        em.close(); //영속 컨텍스트 종료

        member.getName(); //준영속 상태 초기화 시도
```

해당 코드를 보게 되면 `em.close()`메소드로 영속성컨텍스트를 종료해서 `member`는 준영속 상태이다.
`member.getName()`을 하게 되면 프록시를 초기화해야 하지만 영속컨텍스트가 존재하지 않으므로 실제 엔티티 조회가 불가능해 예외가 발생한다.

## 프록시와 식별자

엔티티를 프록시로 조회할 경우 식별자(PK) 값을 파라미터로 전달하는데 프록시 객체는 이값을 보관한다.

```java
Team team=em.getReference(Team.class,"team1"); //식별자 보관
        team.getId(); // 초기화 되지 않음
```

해당 모드를 보면 프록시 객체는 식별자 값을 가지고 있으므로 식별자 값을 조회하는 `team.getId()`를 호출해도 프록시를 초기화 하지 않는다.
(단 엔티티 접근방식을 프로퍼티로 설정한 경우에만 초기화하지 않는다) 엔티티 접근 방식을 필드로 설정하게 되면 해당 메서드가 JPA는 id만 조회하는 메소드인지 다른 필드까지 활용해서 어떤일을 하는 메서드인지 알지
못해서 프록시 객체를 초기화 한다. 이를 이용해 연관관계 설정시 유용하게 사용가능하다.

```java
Member member=em.find(Member.class,"member1");
        Team team=em.getReference(Team.class,"team1"); //SQL 실행안함
        member.setTeam(team);
```

연관관계 설정시 식별자값만 이용하므로 프록시를 이요하게 되면 데이터베이스 접근 횟수를 줄일수 있다.

## 프록시 확인

JPA가 제공하는 `PersistenceUtil.isLoaded(Object entity)` 메서드 이용시 프록시 인스턴스의 초기화 여부 확인이 가능하다. 초기화 되지 않은 프록시 인스턴스는 `false`를
반환한다.

```java
boolean isLoaded=em.getEntityManagerFactory()
        .getPersistenceUnitUtil().isLoaded(entity);
```

조회한 엔티티가 진짜 엔티티인지 프록시로 조회한 것인지 확인하려면 클래스명을 직접 출력해보면 된다.

```java
System.out.println("memberProxy = "+member.getClass().getName());
```

```
결과 : meberProxtt = jpabook.domain.Member_$$_javassist_0
```

결과를 보면 ..javassist..라 되어 있는데 이것을 보고 프록시인 것을 확인할수 있다.(프록시를 생성하는 라이브러리에 따라 출력결과는 달라진다)

## 즉시 로딩과 지연로딩

프록시 객체는 주로 연관된 엔티티를 지연 로딩할때 사용한다.

- 즉시로딩: 엔티티를 조회할때 연관된 엔티티도 함꼐 조회한다.
- 지연로딩 : 연관된 엔티티를 실제 사용할 때 조회한다.

#### 즉시로딩

즉시로딩을 이용하려면 `@ManyToOne`의 `fetch`속성을 `EAGER`로 지정한다.

```java
    public static void ProxyTest(EntityManager em){
        Member member=em.find(Member.class,"회원1");
        Team team=member.getTeam();
        }
```

위의 예제를 실행시 회원과 팀을 즉시 로딩으로 설정했기 때문에 `member`를 조회하는 순간 `team`도 같이 조회한다. 이때 회원과 팀 두 테이블 모두 조회 해야 하므로 쿼르릴 2번 실행한것 같지만
대부분의 `JPA구현체는 즉시로딩을 최적화하기 위해 가능하면 조인쿼리를 이용힌다.`

```sql
    select member0_.username as username1_0_0_,
           member0_.TEAM_ID  as TEAM_ID2_0_0_,
           team1_.name       as name1_1_1_
    from Member member0_
             left outer join
         Team team1_ on member0_.TEAM_ID = team1_.name
    where member0_.username = ?
```

SQL을 살펴보면 회원과 팀을 조인을 통해 쿼리 한번으로 조회한것을 알수있다.

#### 지연 로딩

즉시로딩을 이용하려면 `@ManyToOne`의 `fetch`속성을 `LAZY`로 지정한다.

즉시로딩때 사용했던 코드를 다시 실행해보면 `em.find(Member.class,"회원1");` 를 호출하게 되면 회원만 조회하고 팀은 조회하지 않는다. 같이 조회한 회원의 `team`메머 변수에 프록시 객체를
넣어둔다

```java
Team team=member.getTeam(); // 프록시 객체
```

`반환된 팀 객체는 프록시 객체다. 이프록시 객체는 실제 사용될때 까지 데이터의 로딩을 미룬다. 그래서 이것을 지연로딩이라고 부른다`

## 지연 로딩 활용

사내 주문 관리 시스템

![img_2.png](img_2.png)은

모델 분석

- 회원(Member)은 팀(Team)하나에만 소속할 수 있다. (N:1)
- 회원(Member)은 여러 주문내역(Order)을 가진다. (N:1)
- 주문내역(Order)은 상품정보(Product)를 가진다. (N:1)

애플리케이션 로직

- Member와 연관된 Team은 자주 함께 사용되었다 Member와 Team은 즉시로딩으로 설정
- Member와 연관된 Order은 가끔 사용됨. Member와 Order는 지연로딩으로 설정
- Order와 연관된 Product는 함께 자주 사용됨 Order와 Product는 즉시로딩

회원과 팀은 즉시로딩으로 설정했다. 회원을 조회할때 연관된 Team객체도 함께 조회힌다.

```sql
    select member0_.MEMBER_ID as MEMBER_I1_0_0_,
           member0_.TEAM_ID   as TEAM_ID3_0_0_,
           member0_.username  as username2_0_0_,
           team1_.TEAM_ID     as TEAM_ID1_3_1_,
           team1_.name        as name2_3_1_
    from Member member0_
             left outer join
         Team team1_ on member0_.TEAM_ID = team1_.TEAM_ID
    where member0_.MEMBER_ID = ?
```

- 회원과 팀을 즉시로딩으로 설정했으므로 조인 쿼리를 만들어 회원과 팀을 한꺼번에 조회한다.
- 반면 회원과 주문내역은 지연로딩으로 설정을 했으므로 프록시로 조회해서 SQL에 나타나지 않는다.

#### 프록시와 컬렉션 래퍼

지연 로딩으로 설정하면 실제 엔티티 대신 프록시 객체를 사용한다. 프록시 객체는 실제 자신이 사용될 때까지 데이터베이스를 조회하지 않는다.

```java
    List<OrderDetails> orders=member.getOrders();
        System.out.println("orders = "+orders.getClass().getName());
// 결과 : orders = org.hibernate.collection.internal.PersistentBag
```

하이버네이트는 엔티티를 영속상태로 만들때 엔티티에 컬렉션이 있으면 컬렉션을 추적하고 관리할 목적으로 원본 컬렉션을 하이버네이트가 제공하는 내장 컬렉션으로 변경한다. 이를 컬렉션 래퍼라고 한다.

`엔티티를 지연로딩하면 프록시 객체를 사용해서 지연로딩을 수행하지만 주문내역 같은 컬렉션은 컬렉션 래퍼가 지연로딩을 처리해준다.`

#### JPA 기본 페치 전략

fetch 속성의 기본 설정값은 다음과 같다

- @ManyToOne, @ManyToOne : 즉시로딩
- @OneToMany, @ManyToMany : 지연로딩

JPA의 기본 페치 전략은 연관된 엔티티가 하나면 즉시로딩을, 컬렉션일시 지연로딩을 사용한다.

컬렉션을 로딩하는 것은 비용이 많이든다.

> `추천하는 방법은 모든 연관관계에 지연로딩을 사용하는 것이다.`
>
> 애플리케이션 개발이 어느 정도 완료단계에 왔을 때 실제 사용하는 상황을 보고 꼭 필요한곳에만 즉시로딩을 사용하도록 최적화 한다.

#### 컬렉션 즉시 로딩 사용시 주의점

컬렉션에 `FetchType.EAGER`를 사용할 경우에 주의할 점은 다음과 같다

- 컬렉션을 하나 이상 즉시 로딩하는것은 권장하지 않는다.

> 컬렉션을 조인 한다는것은 DB테이블로 보면 일대다 조인이다. 일대다 조인은 결과 데이터가 다 쪽에 있는 수만큼 증가하게된다. 연관관계가 많으면 많을수록 로딩해야 하는 데이터가 많아지므로 2개이상의 컬렉션은 즉시로딩으로 설정하는 것은 권장하지 않는다

- 컬렉션 즉시 로딩은 항상 외부조인(OUTER JOIN)을 사용한다.

`FetchType.EAGER`설정과 조인 전략

- @ManyToOne, @OneToOne
    - (optional = false) : 내부조인
    - (opthinal = true) : 외부조인


- @OneToMany, @ManyToMany
    - (optional = false) : 외부조인
    - (opthinal = true) : 내부조인

#### 영속성 전이 : CASCADE

```java
    private static void saveNoCascade(EntityManager em){
        // 부모저장
        Parent parent=new Parent();
        em.persist(parent);

        //1번자식 저장
        Child child1=new Child();
        child1.setParent(parent);
        parent.getChildren().add(child1);
        em.persist(child1);

        //2번자식 저장
        Child child2=new Child();
        child2.setParent(parent);
        parent.getChildren().add(child2);
        em.persist(child2);
        }
```

`JPA에서 엔티티를 저장할 때 연관된 모든 엔티티는 영속 상태여야한다.`

따라서 기존의 코드는 부모 엔티를 영속상태로 만들고 자식 엔티티도 각각 영속상태로 만들어야 한다. 이때 영속성 전이를 이용하면 부모만 영속상태로 만들면 연관된 자식까지 한번에 영속 상태로 만들수 있다.

#### 영속성 전이: 저장

```java

@Entity
public class Parent {
    ...
    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST)
    private List<Child> children = new ArrayList<Child>();
    ...
}
```

부모 영속시 연관된 자식들도 영속화가 가능하도록 `cascade = CascadeType.PERSIST` 해당 옵션을 설정했다.

이렇게 하면 간편하게 부모와 자식엔티티를 한번에 영속화 시킬수 있다.

영속성 전이는 연관관계를 매핑하는 것과는 아무 관련이 없으며 단지 엔티를 영속화 할때 연관된 엔티티도 같이 영속화 하는 편리함을 제공한다.

#### 영속성 전이: 삭제

저장한 부모와 자식 엔티티를 모두 제거 하려면 각각의 엔티티를 하나씩 전부 제거해야한다. 하지만 영속성전이는 엔티티를 삭제할 때도 사용할수 있다.

`CascadeType.REMOVE`로 설정하면 부모 엔티티만 삭제하면 연관된 모든 엔티티도 함께 삭제 된다.

```java
Parent parent=em.find(Parent.class,1L);
        em.remove(parent);
```

하지만 `CascadeType.REMOVE` 설정하지 않고 해당 코드를 실행하게 되면 외래키 제약 조건으로 인해 DB외래키 무결성 예외가 발생한다.

#### CASCADE 종류

![img_3.png](img_3.png)

- ALL: 모두적용
- PERSIST : 영속
- MERGE : 병합
- REMOVE : 삭제
- REFRESH : REFRESH
- DETACH : DETACH

그리고 추가적으로 여러속성을 같이 사용이 가능하다
`cacade = {CascadeType.PERSIST,CascadeType.REMOVE}`
> 참고로 CascadeType.PERSIST, CascadeType.REMOVE 는 em.persist(),em.remove()를 실행할 때 바로 전이가 발생하지 않고 플러시를 호출할 때 전이가 발생한다

#### 고아객체

JPA는 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제하는 기능을 제공한다.

이것을 고아 객체 (ORPHAN) 제거 라 한다.

```java
    public static void oprhanRemove(EntityManager em){

        Parent parent=em.find(Parent.class,1L);
        parent.getChildren().remove(0);
        }
```
해당 코드를 실행하면 컬렉션의 첫번째 자식을 삭제하면 데이터베이스에도 반영된다.
>하지만 반영이 되지 않았다.
> 하이버네이트의 버전 변경이후 `CascadeType`과 `orphanRemoval = true`를 같이 사용하여아 한다.
> 같이 사용하면 컬렉션만 제거하면 데이터베이스에 반영이 되는걸 볼수가 있다.