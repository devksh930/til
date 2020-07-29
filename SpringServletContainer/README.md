# 내장 서블릿 컨테이너

## 스프링부트는 서버가 아니다.
 - 톰캣객체생성
 - 포트설정
 - 톰캣에 컨텍스트 추가
 - 서블릿 만들기
 - 톰캣에 서블릿 추가
 - 컨텍스트에 서블릿 맵핑
 - 톰캣실행및대기
 ### 이모든 과정을 보다 상세히 또 유연하게 설정하고 실행해주는것이 바로 스프링부트의 자동 설정.
  - ServletWebServerFactoryAutoconfiguration(서블릿 웹 서버 생성)
   >> TomcatServletWebServeFactoryCustomizer(서버 커스터 마이징)
 - DispatcherServletAutoConfiguration
 >> 서블릿 만들고 등록