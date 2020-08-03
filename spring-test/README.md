# Spring test

- spring-boot-starter-test 추가
> - test스콥으로 추가

## @SpringBootTest
> - @RunWith(SpringRunner.class)랑 같이 사용
> - 빈설정파일은 알아서 찾는(@SpringBootApplication)
- WebEnvironment
> - MOCK : mock servlet environment 내장톰캣 구동 안함 (모킹된 서블릿이라 해당서블릿에 인터랙션을 하고 싶을때는 MockMVC라는걸 사용해야됨)
> - RANDOM_PORT,DEFINE_PORT : 내장톰켓 사용안함.
> - NONE : 서블릿 환경제공 안함

## @MockBean
- ApplicationContext에 들어있는 빈을 Mock으로 만든 객체로 교체
- 모든 @Test마다 리셋됨

## 슬라이스 테스트
- 레이어 별로 잘라서 테스트하고 싶을경우
- @JsonTest
- @WebMvcTest
- @WebfluxTest

## 테스트 유틸
- OutputCapture(spring2.3.2 에서는 OutputCaptureRule)
- TestPropertyValues
- TestRestTemplate
- ConfigFileApplicationContextInitializer