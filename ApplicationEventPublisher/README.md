## ApplicationEventPublisher

- ApplicationContext 가 상속 받고 있는 인터페이스

- 옵저버 패턴의 구현체

>ApplicationContext extends ApplicationEventPublisher
>publishEvent(ApplicationEvent event)

- 이벤트 기반의 프로그래밍을 할때 유용한 인터페이스

- 4.2 이전에는 Applicationevent를 상속 받아야 했지만 이후부터는 상속받을 필요가 없어졌다

- 기본적으로 synchronized

- 순서를 정하고 싶으면 @Order(Ordered.HIGHEST_PRECEDENCE) 사용

>비동기처리 Async
>>springApplication 에서 @EnableAsync 어노테이션을 붙이면 비동기로 실행된다 
>>Async 비동기 처리를 하게 되면 각각의 스레드풀에서 따로 돌고 쓰레드 스케줄링에 따라 어떤 쓰레드가 먼저 실행될지 다름 그래서 Order가 의미가 없음

#### Spring이 제공하는 기본 이벤트

- ContextRefreshedEvent: ApplicationContext를 초기화 했거나 리프래쉬 했을때 발생

- ContextClosedEvent: ApplicationContext를 close()하여 싱글톤 빈 소멸 시점에 발생

- RequestHandledEvent: HTTP 요청을 처리했을 때 발생

