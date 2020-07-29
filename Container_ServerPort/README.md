# 내장 서블릿 컨테이너 응용 - 컨테이너와 서버 포트
 [Use Another WEB Server](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-web-servers.html)
 
 - 다른 서블릿 컨테이너로 변경
 - 웹 서버 사용 하지 않기
 > - application.properteis 에 spring.main.web-application-type=none 추가
 - 포트
 > - server.port
> > - application.properteis에서 server.port=7070
> - 랜덤포트
>> - application.properteis에서 server.port=0
> - ApplicationListener< ServletWebServerInitializedEvent >