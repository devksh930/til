# 3장 영속성 관리
#### 3-1 엔티지 매니저 팩토리, 엔티티 매내저
- 데이터베이스를 하나만 사용하는  어플리케이션은 일반적으로 EntityFactoryManagerFactory를 하나만 생성한다.
<pre><code>EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook")
</code></pre>
- 해당코드를 실행하면 persistence.xml 에 있는 정보를 바탕으로 EntityManagerFactory를 생성한다.
- 엔티티매니저 팩토리는 엔티티매니저를 만드는 공장이다. 공장을 만드는 비용은 상당히 크다
- 한개만 만들어 어플리케이션 전체를 공유하도록 설계되어 있다.
- 반면 엔티티 매니저를 생성하는 비용은 거의 들지 안흔다.
- 엔티티 매니저 팩토리는 thread-safe해 서로다른 thread간 공유가 가능 하다.
- 엔티티 매니저는 thread-safe하지 못해 동시성 문제가 발생하므로 스레드간 공유는 절대 하면 안된다.
- 엔티티 매니저는 커넥션이 필요한 시점까지 커넥션을 얻지 않다.

### 영속성 컨텍스트
- 엔티티를 영구 저장하는 환경 
- 논리적인 개념이다.
- 엔티티매니저를 통해 영속성 컨텍스트에 접근이 가능하고 영속성 컨텍스트를 관리할수 있다.
<pre><code>em.persist(member)</code></pre>
- persist()는 엔티티 매니저를 사용하여 member 엔티티를 영속성 컨텍스트에 저장한다. 

### 엔티티의 생명주기
- 비영속(new/transient): 영속성 컨텍스트와 관계 없는 상태
> <pre><code>Member member = new Member();
> member.setId("member1");
> member.setUsername("회원1");</code></pre>
> - 순수한 객체 상태이며 영속성 컨텍스트나 db와는 관련이 없다
> - 이를 비영속 상태라고 한다.
- 영속(managed): 영속성 컨텍스에 저장상태
> <pre><code>em.persist(member);</code></pre>
> - 엔티티 매니저를 통해 영속성 컨텍스트에 저장했다. 영속성 컨텍스트가 관리하는 엔티티를 영속 상태라고 한다.
> - em.find()나 JPQL을 이용해서 조회한 엔티티도 영속성 컨텍스트가 관리하는 상태라고 한다.
- 준영속(deteached): 영속성 컨텍스트에 저장되었따 분리된상태된
> <pre><code>em.detach(member);</code></pre>
> - 영속성 컨텍스트가 관리하던 영속상태의 엔티티를 영속성 컨텍스트가 관리하지 않으면 준영속 상태가된다.
> - em.close()를 호출해서 영속성컨텍스트를 닫거나, em.clear()를 호출해서 영속성 컨텍스트를 초기화 해도 준영속 상태가 된다.
- 삭제(removed): 삭제된 상태
><pre><code>em.remove(member);</code></pre>
>- 엔티티를 영속성 컨텍스트와 DB에서 삭제 한다.
### 영속성 컨텍스트의 특징
- 영속성 컨텍스트와 식별자값
> - 영속성 컨텍스트는 엔티티를 식별자값으로 구분(@Id로 테이블의 기본 키와 매핑한 값)
> - 영속상태는 식별자 값이 반드시 있어야 한다.(없을시 예외 발생)
- 영속성 컨텍스트와 DB저장
> - jpa는 보통 트랜잭션을 커밋하는 순간 영속성컨텍스에 있는 엔티티를 DB와 동기화 하는데 이것을 flush()라 한다.
- 영속성컨텍스트가 엔티티를 관리 장점
> - 1차캐시
> - 동일성 보장
> - 트랜잭션을 지원하는 쓰기 지연
> - 변경감지
> - 지연로딩
### 엔티티 조회
- 영속성 컨텍스트는 내부에 캐시를 가지고 있다. 이것은 1차캐시라 한다. 영속상태의 엔티티는 모두 이곳에 저장된다.
- 영속성 컨텍스트 내부에 Map이 존재, 키는 @Id로 매핑한 식별자 값은 엔티티 인스턴스이다.
- 엔티티 조회
> #### 1차캐시에서 조회
><pre><code> 
> Member member = new Member();
> member.setId("member1");
> member.setUsername("회원1");
> member.setAge(20);
> //1차캐시에 저장
> em.persist(member);
> //1차캐시에서 조회           
> Member findmember = em.find(Member.class, "member1");
> System.out.println(findmember.getUsername());
> em.remove(member);</code></pre>
- em.find()호출시 우선 1차캐시에서 식별자 값으로 엔티티를 찾는다. 엔티티가 있으면 1차캐시에서 엔티티를 조회한다.
#### db에서 조회
> <pre><code>em.find(Member.class,"member2")</code></pre>
> - member2가 1차캐시에 없으면 DB에서 조회한다.
> - 조회한 데이터로 member2 엔티티를 생성해 1차캐시에 저장한다(영속상태
#### 영속 엔티티의 동일성보장
><pre><code>
> Member a = em.find(Member.class, "memeber2");
> Member b = em.find(Member.class, "memeber2");
> System.out.println("동일성 비교");
> System.out.println(a == b);</code></pre>
> - 해당 코드는 true 동일하다.(실제인스턴스가 같다)
> - 영속성 컨텍스트는 성능상 이점과 엔티티의 동일성을 보장한다.

#### 엔티티 등록
- 엔티티 매니저는 트랜잭션을 커밋 하기 직전까지 entity를 저장하지 않고 내부 쿼리 저장소에 차곡차곡 모아둔다.
- memberA와 memberB를 추가한다고 가정할떄
> <pre><code>persist(memberA); 
> persist(memberB)</code></pre>
> - persist(memberA),persist(membeB)를 member엔티티정보를 영속화 할때마다 member엔티티 정보로 쿼리를 생성 하여 쓰기지연 SQL저장소에 보관한다.
> - commit을 하게되면 flush() 즉 데이터베이스에 동기화 하는 작업 -> 등록,수정,삭제한 엔티티를 데이터베이스에 반영한다.
> - 쓰기지연 SQL저장소에 데이터베이스를 동기화 한 후에 실제 데이터베이스 트랜잭션을 커밋한다.
#### 변경감지(dirty checking)
- jpa는 영속화 된 컨텍스트는 엔티티의 병경사항에 대해서 자동으로 변경을 감지하는 기능이 있다.
- jpa는 엔티티를 영속성 컨텍스트에 보관할때 최초 상태를 복사해서 저장해두는데 이것을 스냅샷 이라고 한다.
- flush하는 시점에 스냅샷과 엔티티를 비교하여 변경된 엔티티를 찾는다.
#### 엔티티 삭제
- 엔티티를 삭제하려면 삭제대상 엔티티를 조회 해야한다.
<pre><code>Member memberA = em.find(Member.class, "memberA");
em.remove(memberA);</code></pre>
- remover를 한다고 바로 삭제 하는것이 아닌 쿼리를 쓰기지연 저장소에 등록한다.
- 삭제된 컬렉션은 자연스럽게 GC의 대상이 되도록 두는 것이 좋다.

#### flush()
- 플러시는 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영한다.
1. 변경감지기가 동작 하여 영속성 컨텍스트에 있는 모든 엔티티를 스냅샷과 비교 하여 수정된 엔티티를 찾아 수정 쿼를 만들어 지연 sql 저장소에 등록한다.
2. 쓰기지연 sql저장소의 쿼리를 DB에 전송한다(등록,수정,삭제 쿼리).
> 영속컨텍스트 플러시하는 방법
> - em.flush()를 직접호츨
>> - flush() 메소드를 직접 호출하여 강제로 플러시한다. 테스트, 다른프레임워크와 jpa를 함께 사용할떄를 제외 하고 사용할 일이 많이 없다.
> - 트랜잭션 커밋 시 플러시가 자동 호출
>> - 트랜잭션을 커밋만 하게 되면 변경내용을 sql로 전달하지 않는다. 커밋을 하기전 플러시를 호출해 변경내용을 db에 반영해야한다. jpa는 commit() 시점에 flush()를 자동으로 호출하게 된다.
> - JPQL 쿼리 실행 시 플러시가 자동 호출
### 준영속
- 영속상태의 엔티티가 영속성 컨텍스트에 분리된 것을 준영속 상태라 한다.
1. em.detach(entity) : 특정 엔티티만 준영속 상태로 전환.
2. em.clear() : 영속성 컨텍스트를 완전히 초기화
3. em.close() : 영속성 컨텍스트를 종료.

#### detach()
- detach() 메소드는 특정 엔티티를 준영속 상태로 만든다.
- 해당 메소드 호출시 1차 캐시부터 쓰기지연 sql저장소 까지 해당 네티티를 관리하기 위한 모든 정보가 제거된다.
#### clear()
- 영속성 컨텍스트에 있는 모든 것이 초기화 된다.
- 영속성 컨텍스트를 제거하고 새로만든것과 같다.
#### close()
- 영속성컨텍스트를 종료 한다.
#### merge()
- merge() 메소드는 준영속 상태의 엔티티를 받아 그 정보로 새로운 영속상태의 엔티티를 반환한다.
- [예제](./src/main/java/devksh930/JpaMerge.java) 
> - merge() 실행
> - 파라미터로 넘어온 준영속 엔티티의 식별자 값을 1차캐시에서 엔티티 조회
> - 1차 캐시에 없을 경우 db에서 엔티티조회후 1차캐시에 저장
> - 조회한 영속 엔티티(mergeMember)에 meber 엔티티의 값을 채워 넣는다(이때 mergeMember의 '회원1' 이라는 값이 '회원명변경'으로 바뀐다.) 
> - mergeMember를 반환
