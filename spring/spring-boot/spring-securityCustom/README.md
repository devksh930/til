# Spring-Security 커스터마이징
### 1. 웹 시큐리티 설정
<pre><code>
 @Configuration
 public class SecurityConfig extends WebSecurityConfigurerAdapter {
     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http.authorizeRequests()
                 .antMatchers("/", "/hello").permitAll()
                 .anyRequest().authenticated()
                 .and()
                 .formLogin()
                 .and()
                 .httpBasic();
     }
}</code></pre>
- hello와 index는 인증을 안해도 접속가능
- 다른 리퀘스트에 대해서는 인증을 하도록
- UserDetailsServie 구현
> - https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#jc-authentication-userdetailsservice
- PasswordEncoder 설정 및 사용
> - https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#core-services-password-encoding