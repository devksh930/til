# RestTemplate과 WebClient
### RestTemplate
- Blocking I/O 기반의 Synchronous API
- RestTemplateAutoConfiguration
- 프로젝트에 spring-web 모듈이 있다면 RestTemplateBuilder를 빈으로 등록해 줍니다.
- https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#rest-client-access
 
### WebClient
- Non-Blocking I/O 기반의 Asynchronous API
- WebClientAutoConfiguration
- 프로젝트에 spring-webflux 모듈이 있다면 WebClient.Builder를 빈으로 등록해 줍니다.
- https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-client