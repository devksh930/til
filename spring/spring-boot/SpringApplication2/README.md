## SpringApplication2

- spirngApplication event
><SampleListner>ApplicationStatingEvent는 applicationContext가 등록되기전에 실행이된다 하지만 이 listener는 applicationContext가 등록이되면서 @Component로 등록이 되서
 작동하지않는다 직접 이벤트를 등록해줘야한다.(ApplicationContext를 만들기 전에 사용하는 리스너는 @Bean으로 등록할 수 없다)
>- 이외의 것들은 @Bean으로만 등록하면 사용가능하다.
- WebApplication Type 설정
>        app.setWebApplicationType(WebApplicationType.NONE);
- Application Argument
> -  -D옵션은 JVM옵션 --옵션으로 아규먼트 설정가능
> - ApplicationArguments를 빈으로 등록해 주니깐 가져다 쓰면 됨
- Application을 실행한 뒤 뭔가 하고 싶을경우
> - ApplicationRunner 또는 CommandLineRunner
> - 순서지정가능 @Order(1) 이런식으로 우선순위가 지정이 가능하다.