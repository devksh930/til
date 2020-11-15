# 자동설정

## EnableAutoConfiguration(SpringBootApplication 안에 숨어 있음)
 - 빈은 사실 두 단계에 나눠서 읽힌다
 > - 1단계: @ComponentScan
 >-  2단계: @EnableAutoConfiguration
## @ComponentScan
 >@Component라는 에노테이션을 가진 클래스들을 빈으로 등록한다.
- @Component
- @Configuration @Repository @Service @Controller @RestController
## @EnableAutoConfiguration
  - Spring.factories 
 > org.springframework.boot.autoconfigure.EnableAutoConfiguration
 - @Configuration
 - @ConditionalOnXxxYyyZzz


## 자동설정 구현

- Xxx-spring-boot-Autoconfigure 모듈 : 자동설정
- Xxx-spring-Starter 모둘 : 필요한 의존성 정의
- 하나로 만들고 싶을때는
 >xxx-spring-boot-starter
 - 구현방법
 > - 1.의존성 추가
>[pom.xml](./pom.xml)
> - 2.[@Configuration ](./src/main/java/me/devksh930/HolomanConfiguration.java) 작성
>- 3.resources/META-INF/[spring.factories](./src/main/resources/META-INF/spring.factories) 파일 만들기
>- 4.[spring.factories](./src/main/resources/META-INF/spring.factories) 안에 자동 설정 파일 추가
> - 5.mvn install
 - 덮어쓰기 방지
 > 자동세팅하는 Bean에 @ConditionalOnMissingBean 을 사용하면 Component-scan이 우선시 된다
 - 빈 재정의 수고 덜기
 > - @ConfigurationProperties(“holoman”)
> - @EnableConfigurationProperties(HolomanProperties)
> - 프로퍼티 키값 자동 완성