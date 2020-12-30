# 스프링 부트에서 jsp 사용하기

- springboot 레퍼런스에서 boot에서의 jsp 사용을 권장하지 않는다.

> “If possible, JSPs should be avoided. There are several known limitations when using them with embedded servlet containers.”
> - https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-spring-mvc-template-engines

제약사항

톰캣이나 jsp, 서블릿컨테이너에 배포시에는 아래의 제약사항이 크게 문제가 없음.

- JAR 프로젝트로 만들 수 없음, WAR 프로젝트로 만들어야 함
- Java -JAR로 실행할 수는 있지만 “실행가능한 JAR 파일”은 지원하지 않음
- 언더토우(JBoss에서 만든 서블릿 컨테이너)는 JSP를 지원하지 않음
- Whitelabel 에러 페이지를 error.jsp로 오버라이딩 할 수 없음.
