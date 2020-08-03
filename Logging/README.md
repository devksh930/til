# 로깅
### 로깅 퍼사드 VS 로거
- Commons Logging을 써서 ->SLF4j로 가고  -> 최종적으로 Logback로 간다
- 로깅 퍼사드
> - Commons Logging, SLF4j
> - 실제로깅 하는 것이 아니라 logger API들을 추상화해논 인터페이스들이
> - 프레임웍들은 로깅퍼사드를 이용해서 프로그래밍을 많이한다.
> - 로깅퍼사드의 장점은 로거를 쉽게 바꿀 있다.
- 스프링5에 대한 로거 변경사항
> - 과거에는 SLF4j를 쓰기위해서 pom.xml에 여러가지 의존성 설정이 필요했다.
> - 의존성 설정을 안해도 안전하게 쓸수 있도록 JCL이라는 모듈을 만듬
- Spring-JCL
> - JCL코드를 컴파일하는 시점에 SLF4j나 Log4j2로 변경할수 있는 모듈
### 스프링부트 로깅
- --debug(일부 핵심 라이브러리만 디버깅 모드로)
- --trace(전부다 디버깅 모드로)
- 컬러출력: spring.output.ansi.enabled
- 파일출력: 스프링2.32 버전에서는 logging.file.path 또는 logging.file.name (스프링 2.32 이전버전에서는 logging.file 또는 logging.path)
- 로그레벨조정: logging.level.패키지 = 로그레벨