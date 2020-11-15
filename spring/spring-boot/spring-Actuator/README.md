# SpringBoot Actuator
-  springboot Actuator는 애플리케이션을 모니터링 하고 운영하기 위한 편리한 기능을 제공함
- HTTP또는 JMX 엔드 포인트를 사용하여 에플리케이션을 관리하고 모니터링 하도록 선택할 수 있다.

- 의존성 추가 spring-boot-starter-actuator

- actuator는 엔드포인트를 제공해준다
> - auditevents : 인증이벤트를 누가 획득했 누가 인증을 실패했는지
> - beans : 등록된 빈들
> - conditions : 어떤 자동설정이 어떤 조건에 의해서 적용이 되었고 적용이 되지 않았는지
> - configprops : application.properties에 적용 가능한 프로퍼티를 보여준다 
> - env : Spring의 env안에 등록된 프로퍼티들을 보여주는것
> - flyway : flyway마이크레이션 정보를 보여줌
> - health : application이 잘 구동중인지 상태를 보여줌
> - httptrace : 최근 100개의 요청과 응을 보여줌 
> - info : 어플리케이션 임의의 정보를 보여줌
> - loogers : 어떤패키지가 어떤 로깅레벨을 가지고 있는지 또는 그런 로깅 레벨을 수정가능
> - metrics : application의 핵심이 되는정보 메모리,cpu 같은 정보들을 써드파티 모니터링 어플리케이션에서 사용할수 있는 공동의 포맷으로 정보블 제공한다. 
> - mappings : 컨트롤러의 맵핑 정보를 보여줌
> - scheduledtasks : 주기적으로 batch를 돌릴 task를 보여줌
> - session : spring-session
> - shutdown : application을 종료 할수있는 endpoint 비활성화 되잇음
> - threaddump : 쓰레드 덤프를 뜰수있다 .
- JMX 또는 HTTP를 통해 접근 가능 함
- shutdown을 제외한 모든 Endpoint는 기본적으로 할성화 상태

### JConsole 사용하기
- https://docs.oracle.com/javase/tutorial/jmx/mbeans/
- https://docs.oracle.com/javase/7/docs/technotes/guides/management/jconsole.html
### VisualVM 사용하기
- https://visualvm.github.io/download.html
###HTTP 사용하기
- /actuator
> - health와 info를 제외한 대부분의 Endpoint가 기본적으로 비공개 상태
- 공개 옵션 조정
>- management.endpoints.web.exposure.include=*
>- management.endpoints.web.exposure.exclude=env,beans

### Spring-boot Admin
- spring 써드파티 라이브러리 
- actuator에서 제공 하는 정보를 visualization 해줌
- https://github.com/codecentric/spring-boot-admin 스프링 부트 Actuator UI 제공 어드민 서버 설정
> - spring-boot-admin-starter-server 의존성추가
> - 어플리케이션에 @EnableAdminServer 추가

- 클라이언트 설정
> - spring-boot-admin-starter-client 의존성추가
> - 프로퍼티 설정 spring.boot.admin.client.url=http://localhost:8080, management.endpoints.web.exposure.include=*