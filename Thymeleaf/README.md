## Thymeleaf
###  스프링 부트가 자동 설정을 지원하는 템플릿 엔진
- 주로 View를 만든는데 사용, 코드제네레이션, email템플릿 등
- FreeMarker
- Groovy
- <b>Thymeleaf</b>
- Mustache

### <b>Jsp를 권하지 않는 이유</b>
- JAR 패키징 할 때는 동작하지 않고 WAR 패키징을 해야 함.
- Undertow는 JSP를 지원 하지 않음

### Thymeleaf 사용하기
- https://www.thymeleaf.org/ 레퍼런스
- 의존성 추가 : spring-boot-starter-thymeleaf
- 템플릿 파일 위치 : /src/main/resources/template/