# AOP

## AOP란?
- Aop 흩어진 aspect를 모듈화 할수 있는 프로그래밍 기법
- oop를 보완하는 수단
- 흩어진 관심사를 concern
> 여기서 Concern이란  여러 클래스 여러메소드에 걸쳐서 나타나는 비슷한 코드를 의미함
- 만약 concern의 변경이 일어난다면 그 concern을 사용하는 모든 소스코드를 손을 봐야한다
 유지보수 측면에서 문제가 있다.
 > aop는 이 문제를 aspect로 해결한다
 > - 흩어져있는 concern들을 한군데 모은다
    독립적으로 concern을 정의하고 어디에 적용 해야하는지 알려주면 된다.
    해야할일과 그일을 어디에 적용 시켜야하는지 묶어서 모듈화 한다는 개념

## AOP 주요개념
 - Aspect는 모듈
 - Asepct 안에는 advice, pointcut이 들어간다.
 - advice는 해야할일, pointcut은 어디에 적용 해야할지
 - target은 classA, classB등 적용대상을 의미
 - joinpoint는 aspect가 끼어들 지점(생성자호출하기직전, 생성자호출후, 필드에접근하기전, 필드에 접근하여 값을 가져갔을때 등 여러가지 합류점

## AOP 적용방법
 - 컴파일시 조작이된 바이트코드를 생성하는 방법
 - 로드 타임 class파일을 로드하는 시점에 byte코드를 로딩하는 클래스 정보를 변경하는 시점 로드 타임 위빙을 을해서 jvm메모리상에는 aop가 적용된 클래스가 적용된다
 - 런타임 spring aop가 사용하는방법 bean을 만들때 proxy빈을 만든다 proxy빈이 aop를 적용 하고 해당 빈을 호출한다.

## Spring AOP
 - 프록시 기반의 AOP 구현체
 - 스프링 빈에만 AOP 적용
 - 모든 AOP기능을 제공하는것이 아닌 스프링IOC와 연동하여 엔터프라이즈 애플리케이션에서 가장 흔한 문제에 대한 해결첵을 제공하는 것이 목적  
 
 ## 프록시 패턴
 img proxy
 
 - 기존 코드를 건드리지 않고 접근제어, 부가기능을 추가하기 위해 쓰임
 
 ## 문제
 <pre>
 <code>@Primary
       @Service
       public class ProxySimpleEventService implements EventService {
           @Autowired
           SimpleEventService simpleEventService;
       
           @Override
           public void createEvent() {
               long begin = System.currentTimeMillis();
               simpleEventService.createEvent();
               System.out.println(System.currentTimeMillis() - begin);
           }
       
           @Override
           public void publishEvent() {
               long begin = System.currentTimeMillis();
               simpleEventService.publishEvent();
               System.out.println(System.currentTimeMillis() - begin);
       
           }
       
           @Override
           public void deleteEvent() {
               simpleEventService.deleteEvent();
           }
       }
 </code>
 </pre>
 
 - 해당 방법은 EventService에 위임하여 성능을 측정할수 있는 방법이다 하지만 이경우 코드가 중복되는 경우가 있고 매번프록시 클래스를 작성을 해야하고 여러 클래스 여러메소드에 적용하려면 복잡하고 객체들 관계에도 문제가 생긴다.
 - 동적프록시를 통한 해결
 ## 동적프록시
 - AbstractAutoProxyCreator를 이용하여 해당 빈을 proxy로 감싸주는 역할을 함 (BeanPostProceesor의 구현체)
 > BeanPostProceesor 빈이 등록이되면 가공할수 있는 라이프사이클
 - PointCut과 JoinPoint를 정의 해야함
 ###Aspect 정의
 - @Aspect
 > 빈으로 등록해야함 (컴포넌트 스캔을 사용한다면 @Component cnrk)
 - Poitcut 정의
>- @annotation
>- Execution
>- Bean
 - 어드바이스 정의
>- @Before
>- @AfterReturning
>- @AfterThrowing
>- @Around