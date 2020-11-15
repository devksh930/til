# 외부설정

#### 사용할수 있는 외부설정 
- properties
> - 방법이다양해서 우선 순위가 존재한다.
 - 프로퍼티 우선 순위
> 1. 유저 홈 디렉토리에 있는 spring-boot-dev-tools.properties
> 2. 테스트에 있는 @TestPropertySource
> 3. @SpringBootTest 애노테이션의 properties 애트리뷰트
> 4. 커맨드 라인 아규먼트 java -jar springproject.jar --devksh930.name=ksh
> 5. SPRING_APPLICATION_JSON (환경 변수 또는 시스템 프로티) 에 들어있는 프로퍼티
> 6. ServletConfig 파라미터  
> 7. ServletContext 파라미터
> 8. java:comp/env JNDI 애트리뷰트
> 9. System.getProperties() 자바 시스템 프로퍼티
> 10. OS 환경 변수
> 11. RandomValuePropertySource
> 12. JAR 밖에 있는 특정 프로파일용 application properties
> 13. JAR 안에 있는 특정 프로파일용 application properties
> 14. JAR 밖에 있는 application properties
> 15. JAR 안에 있는 application properties
> 16. @PropertySource
> 17. 기본 프로퍼티 (SpringApplication.setDefaultProperties)
>> - @Value("${devksh930.name}") 해당방법은 15위이다.
- application.properties 우선 순위 (높은게 낮은걸 덮어 씁니다.) 
> 1. file:./config/
> 2. file:./
> 3. classpath:/config/
> 4. classpath:/

- 랜덤값 설정하기
> ${random.*}
- 플레이스 홀더
> <p> name = keesun </p>
> <p> fullName = ${name} baik</p>
