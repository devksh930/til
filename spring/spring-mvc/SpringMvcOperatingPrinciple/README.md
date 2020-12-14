# 스프링 MVC 동작원리

- DispatcherServlet은 결국엔 굉장히 복잡한 Servlet이다.

- DipatcherServlet chrlghk
    - 특정 타입에 해당하는 빈을 찾느다.
    - 없으면 기본 전략을 사용한다 (DispatcherServlet.properties)

- 스프링 부트를 사용하지 않는 스프링 MVC
    - 서블릿 컨테이너(ex. tomcat)에 등록한 웹앱(war)에 DispatcherServlet을 등록
  > - web.xml에 등록
  > - 또는 WebApplicationInitializer에 자바 코드로 서블릿 등록
    - 세부한 구성요소는 빈설정 나름

- 스프링 부트를 사용 하는 스프링 MVC
    - 자바 애플리케이션에 내장 톰캣을 만들고 그 안에 DispatcherServlet을 등록
        - 스프링부트에서 자동설정이 자동으로 해준다.
    - 스프링부트의 주관에 따라 여러 인터페이스 구현체를 빈으로 등록한다.

# @EnableWebMvc

- 애노테이션 기반 스프링 MVC를 사용할 때 편리한 웹 MVC 기본설정

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig() {
}
```

# WebMvcConfigurer 인터페이스

- @EnableWebMvc가 제공하는 빈을 커스터마이징 할수 있는 기능을 제공하는 인터페이스

```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/", ".jsp");
    }
}
```
- 원하는 기능을 확장을 하려면 WebMvcConfigurer 구현하면 된다.
- addFromatters
- addInterceptors
- addViewControllers
- addCorsMappings
- addArgumentResolvers
- ...등
