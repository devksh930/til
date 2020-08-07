# Spring-Security
### 스프링 시큐리티
- 웹 시큐리티
- 메소드 시큐리티
- 다양한 인증방법 지원
> - LDAP, 폼인증, Basic인증, OAuth,...
### 스프링 부트 시큐리티 자동 설정
 - SecurityAutoconfiguration
- UserDetailsServiceAutoconfiguration
> <pre><code>@ConditionalOnMissingBean({AuthenticationManager.class, AuthenticationProvicer.class, UserDetailsService.class}) </code></pre>
> - 위 설정에 의해서 이 3개의 클래스가 구현되지 않을시 UserDetailsServiceAutoConfiguration 내의 설정이 적용 되며 inMemoryUserDetailsManager()에 의해서 UserDetailsManager가 생성 된다
> - UserDetailsService.class를 재정의 하면 해당 랜덤한 유저는 생성이 되지 않는다. 
- 모든 요청에 인증이 필요함.
- 기본 사용자 생성
> - Username: user
> - Password: 애플리케이션을 실행할 때 마다 랜덤 값 생성 (콘솔에 출력 됨.)
> - spring.security.user.name
> - spring.security.user.password
- 인증 관련 각종 이벤트 발생
> - DefaultAuthenticationEventPublisher 빈 등록
> - 다양한 인증 에러 핸들러 등록 가능
                                                                              

 