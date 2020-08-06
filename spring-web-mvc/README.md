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
## 정적 리소스 지원
> - 정적 리소스 맵핑 “ /**”
> - 기본 리소스 위치
> - classpath:/static
> - classpath:/public
> - classpath:/resources/
> - classpath:/META-INF/resources
> > - 예) “/hello.html” => /static/hello.html
> - spring.mvc.static-path-pattern: 맵핑 설정 변경 가능
> - spring.mvc.static-locations: 리소스 찾을 위치 변경 가능
> - Last-Modified 헤더를 보고 304 응답을 보냄.
- ResourceHttpRequestHandler가 처리함.
> - WebMvcConfigurer의 addRersourceHandlers로 커스터마이징 할 수 있음
 <pre><code>            @Override
             public void addResourceHandlers(ResourceHandlerRegistry registry) {
               registry.addResourceHandler("/m/**")
                 .addResourceLocations("classpath:/m/")
                 .setCachePeriod(20);
}</code></pre>

## web jar
- npm이나 부트스트랩, jquery등 jar파일로 가져올수 있다.
- 웹Jar 맵핑 "/webjars/**"
> - 버전 생략하고 사용하려면 
>> - webjars-locator-core 의존성추가(리소스 체이닝)
>> <pre><code>src="/webjars/jquery/3.5.1/dist/jquery.min.js" => src="/webjars/jquery/dist/jquery.min.js"</code></pre>
## index 페이지와 파비콘
###index
- index.html 찾아보고 있으면 제공 
- index,템플릿 찾아보고 있음녀 제공
- 둘 다없을시 에러페이지(tomcat이 아닌 spring에서 제공하는 404error 페이지 띄움)

### 파비콘
- favicon.ico
- 스프링에서 자동으로 지원함
