# 로깅 커스터마이징
### 커스텀 로그 설정 파일 사용하기
- Logback: Logback-spring.xml
- Log4J2: log4j2-spring.xml
- JUL(비추) logging.properties
- Logback을 Log4j2로 변경하기 spring
<pre>
<code><dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-starter-web</artifactId>
                  <exclusions>
                      <exclusion>
                          <groupId>org.springframework.boot</groupId>
                          <artifactId>spring-boot-starter-logging</artifactId>
                      </exclusion>
                  </exclusions>
              </dependency>
      
              <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-starter-log4j2</artifactId>
              </dependency></code>
</pre>