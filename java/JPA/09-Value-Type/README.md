# 값 타입

JPA 데이터 타입

- 기본값 타입(basic value type)
    - 자바 기본 타입(예: int, double)
    - 래퍼 클래스 (예: Integer)
    - String
- 임베디드 타입(복합 값 타입)
- 컬렉션 값 타입

기본값 타입은 `String`,`int`처럼 자바가 제공하는 기본 데이터타입

임베디드 타입은 JPA에서 사용자가 직접 정의한 값 타입

컬렉션 값 타입은 하나 이상의 값 타입을 저장할 때 사용한다.

### 기본값 타입

```java

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
}
```

Member에서 String, int가 값 타입이다.

### 임베디드 타입(복합 값 타입)

새로운 값 타입을 직접 정의해서 사용할수있다.

중요한것은 임베디드 타입도 `int`,`String`처럼 값타입이다.

```java

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    //근무기간
    @Temporal(TemporalType.DATE)
    java.util.Date startDate;
    @Temporal(TemporalType.DATE)
    java.util.Date endDate;

    //집주소
    private String city;
    private String street;
    private String zipcode;
}
```

기존의 코드를 보면 회원이 상세한 데이터를 그대로 가지고 있어서 객체지향적이지 않으며 코드의 응집력만 떨어트린다.

```java

@Entity
public class Member {
    ...
    //근무기간
    @Embedded
    Period period;
    //집주소
    @Embedded
    Address address;
  ...
}

@Embeddable
public class Address {
    @Column(name = "city")
    public String city;
    private String street;
    private String zipcode;
}

@Embeddable
public class Period {
    @Temporal(TemporalType.DATE)
    java.util.Date startDate;
    @Temporal(TemporalType.DATE)
    java.util.Date endDate;
}
```

변경된 코들르 보면 회원 엔티티가 더욱 의미있고 응집력 잇게 변한것을 볼수 있다.

임베디드 타입을 사용하려면 2가지 어노테이션이 필요하다

-`@Embeddable`: 값 타입을 정의하는 곳에 표시 -`@Embedded`: 값 타입을 사용하는곳에 표시

- 임베디드 타입을 포함한 모든 값 타입은 엔티티의 생명주기에 의존한다
- 엔티티와 임베디드 타입의 관계는 `컴포지션`관계이다.

> 참고
> - 하이버네이트는 임베디드 타입을 컴포넌트라 한다

### 임베디드 타입과 테이블 매핑

![img.png](img.png)

임베디드 타입은 엔티티의 값일 뿐이다. 임베디드타입이 속한 엔티티 테이블에 매핑한다.

임베디드 타입 덕분에 객체와 테이블을 아주 세밀하게 매핑하는 것이 가능하다.

> - 임베디드 타입과 UML
>
> UML에서 임베디드 값타입을 단순하게 표현가능하다
>
> ![img_1.png](img_1.png)

### 임베디드 타입과 연관관계

임베디드 타입은 값 타이블 포함하거나 엔티티를 참조할 수 있다.

```java

@Entity
@Getter
@Setter
public class Member {
...
    @Embedded
    Address address;
    @Embedded
    PhoneNumber phoneNumber;
...
}

@Embeddable
public class Address {
    public String city;
    private String street;
    @Embedded
    Zipcode zipcode;
}

@Embeddable
public class Zipcode {
    String zip;
    String plusFour;
}

@Embeddable
public class PhoneNumber {
    String areaCode;
    String localNumber;
    @ManyToOne
    PhoneServiceProvider provider;
}

@Entity
public class PhoneServiceProvider {
    @Id
    String name;
}
```

- 값 타입인 `Address`가 값 타입인 `Zipcode`를 포함한다
- 값 타입인 `PhoneNumber`가 엔팉티 타입인 `PhoneServiceProvider`를 참조한다

### `@AttributeOverride` 속성 재정의

- 임베디드 타입에 정의한 매핑정보를 재정의하려면 엔티티에 `@AttributeOverride`를 사용하면 된다.
- `@AttributeOverrides`를 이용하면 메핑할 컬럼명이 중복되는걸 재정의할수 있다.

```java
public class Member {
    ...
    @Embedded
    Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
            @AttributeOverride(name = "city", column = @Column(name = "COMPANY_ZIPCODE"))

    })
    Address companyAddress;
    
...
}
```

- `@AttriuteOverride`를 사용하면 어노테이션을 너무많이 해서 엔티티 코드가 지저분해 진다.

### 임베디드 타입과 null
임베디드 타입이 `null`이면 매핑한 컬럼 값은 모두 `null`이다.
```java
member.setAdress(null);
em.persist(member);
```
- 회원 테이블 주소와 관련된 city,street,zipcode컬럼의 값은 모두 null이 된다. 

## 값 타입과 불변 객체
값타입은 복잡한 객체 세상을 조금이라도 단순화 시키려고 만든 개념이다. 값타입은 단순하고 안전하게 다룰수 있어야한다.

### 값 타입 공유 참조
임베디드 타입은 값 타입을 여러 엔티티에서 공유하면 위험하다.
```java
member1.setHomeAddress(new Address("OldCity"));
Address address = member1.getHomeAddress();

address.setCity("NewCity");
member2.setHomeAddress(address);
```

회원2에 새로운 주소를 할당하려 회원1의 주소를 그대로 참조해서 사용하면 회원1의 주소도 "NewCity" 로 변경된다.

### 값 타입 복사
값 타입의 실제 인스턴스인 값을 공유하는 것은 위험하다.
대신에 값(인스턴스)을 복사해서 사용해야 한다.
```java
member1.setHomeAddress(new Address("OldCity"));
Address address = member1.getHomeAddress();

Address newAddrss = address.clone();

address.setCity("NewCity");
member2.setHomeAddress(address);
```
- 이처럼 항상 값을 복사해서 사용하면 공유 참조로 인해 발생하는 부작용을 피할수 있다.
- 객체를 대입할 때마다 인스턴스를 복하해서 대입하면 공유 참조를 피할수 있다.
- 부작용을 막는 방법은 `Address`객체의 `setCity()` 같은 수정자 메소드를 제거 하면 공유 참조를 해도 값을 변경하지 못하므로 부작용의 발생을 막을수 있다.

### 불변 객체
- 값 타입은 부작용 걱정 없이 사용할 수 있어야 한다
- 값타입은 될 수 있으면 불변객체(`immutable Object`)로 설계 해야한다.
- 불변객체를 구현하는 가장 간단한 방법
  - 생성자로만 값을 설정한다.
  - 수정자(setter)를 만들지 않는다
  
### 값 타입 비교
```java
int a = 10;
int b = 10;

Address a = new Address("서울시","종로구","1번지");
Address b = new Address("서울시","종로구","1번지");
```
- int a의 숫자 int b의 숫자 10은 같다고 표현한다.
- Address a 와 Address b는 같다고 표현한다.

객체 비교
- 동일성(Identity) 비교 : 인스턴스의 참조 값을 비교 , == 사용
- 동등성(Equivalence) 비교 : 인스턴스의 값을 비교 , equals() 사용
>
> Address 값 타입을 a == b로 동일성 비교시 둘은 서로 다른 인스턴스이므로 결과는 거짓이다.
>
>값타입은 인스턴스가 달라도 값이 같으면 같은것으로 보아야한다.
>
> 값타입 비교시 `a.equals(b)`를 사용해 동등성 비교를 해야한다.

### 값 타입 컬렉션
값 타입을 하나 이상 저장하려면 컬렉션에 보관하고 `@ElementCollection`,`@CollectionTable` 어노테이션을 사용하면된다.
```java
public class Member {
 ...
  @ElementCollection
  @CollectionTable(name = "FAVORITE_FOODS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
  @Column(name = "FOOD_NAME")
  private Set<String> favoriteFoods = new HashSet<String>();

  @ElementCollection
  @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
  @Column(name = "ADDRESS_HISTORY")
  private List<Address> addressHistory = new ArrayList<Address>();
}
```
- Member 엔티티에서 `favoriteFoods`와 `addressHistroy`는 컬렉션 타입이다.
- RDB에서 테이블의 컬럼은 `Collection`을 가질수 없다.
- 별도의 테이블로 메핑을 하여야한다.
- 값 타입 컬렉션도 조회할 때 페치 전략을 선택할 수 있다.(`LAZY`가 기본)

#### 값 타입 컬렉션 사용
```java
    public static void collectionValueSave(EntityManager em) {
        Member member = new Member();

        //임베디드 값타입
        member.setHomeAddress(new Address("부산","북구","12345"));

        //기본값 타입 컬렉션
        member.getFavoriteFoods().add("짬뽕");
        member.getFavoriteFoods().add("짜장");
        member.getFavoriteFoods().add("탕수육");

        //임베디드 값 타입 컬렋녀
        member.getAddressHistory().add(new Address("부산", "해운대구", "612768"));
        member.getAddressHistory().add(new Address("부산", "사상구", "612768"));

        em.persist(member);
    }
```
- `meber`엔티티만 마지막에 영속화를 했다. JPA는 `memeber`엔티티의 값 타입도 함께 저장한다 실행되는 `INSERT SQL`
  - `meber` : `INSERT SQL` 1번
  - `member.homeAddress` : 컬렉션이 아니라 임베디드 값타입 이므로 회원테이블에 저장하는 SQL에 포함
  - `member.favoriteFoods` : `INSERT SQL` 3번
  - `member.addressHistroy` : `INSERT SQL` 2번
  
총 6번의 `INSERT SQL`을 실행한다

### 값 타입 컬렉션 제약사항
- 일대다 관계를 위한 엔티티를 만들고, 여기에서 값 타입을 사용
- 영속성 전이(Cascade) + 고아객체제거를 사용해서 값 타입 컬렉션처럼사용!
- 실무에서는 상황에 따라 값 타입 컬렉션 대신에 일대다 관계를 고려
> - 참고 
> 
>값 타입 컬렉션을 변경했을때 JPA 구현체들은 테이블의 기본 키를 식별해서 변경된 내용만을 반영하려고 노력한다.
>하지만 사용하는 컬렉션이나 여러 조건에 따라 기본 키를 식별할 수도 있고 식별하지 못할 수도 있다.
>따라서 값 타입 컬렉션을 사용할 때는 모두 삭제하고 다시 저장하는 최악의 시나리오를 사용하며 사용해야함
