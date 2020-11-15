##ExceptionHandler

### 스프링 MVC 예외 처리 방법
 - @ControllerAdvice
 - @ExchangeHandler
 
### 스프링 부트가 제공하는 기본 예외 처리기
- BasicErrorController
> - HTML과 JSON 응답 지우너
- 커스터마이징 방법
> - ErrorController 구현

### 커스텀 에러페이지
- 상태 코드 값에 따라 에러 페이지 보여주기
- src/main/resoources/static/error/
- src/main/resources/templates/error/
- 404.thml
- 5xx.thml
- ErrorViewResolver 구현