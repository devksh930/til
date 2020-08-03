## 스프링 웹 MVC
- https://docs.spring.io/spring/docs/5.0.7.RELEASE/spring-framework-reference/web.html#spring-web
- 스프링 부트 MVC
> - 자동 설정으로 제공하는 여러 기본 기능 (앞으로 살펴볼 예정)
- 스프링 MVC 확장
> - @Configuration + WebMvcConfigurer
- 스프링 MVC 재정의
> - @Configuration + @EnableWebMvc
## HttpMessageConverter
- Springframework에서 제공하는 인터페이스이고 springmvc의 일부분이다.
-요청 본문을 객체로 변경하거나, 객체를 HTTP 응답 본문으로 변경할 때 사용 {“username”:”keesun”, “password”:”123”} <-> User
## 뷰리졸버
- accept헤더에 따라 응답이 달라진다. 