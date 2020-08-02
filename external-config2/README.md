# Spring External-config2 
### 타입-세이프 프로퍼티 @ConfigurationProperties
- 여러 프로퍼티를 묶어서 읽어올 수 있음
- 빈으로 등록해서 다른빈에 주입할 수 있음
> - @EnableConfigurationProperties(기본적으로 설정되있어서 생략가능)
> - 빈으로 등록할 프로퍼티클래스를 @Component로 등록
> - @ConfigurationProperties("가져올 프로퍼티이름")
> - 사용할곳에서는 빈으로 주입받아서 사용할수 있음
- 융통성 있는 바인딩이 가능함(어떤 값이라도 변환해서 읽음)
> - context-path (케밥)
> - context_path (언드스코어)
> - contextPath (캐멀)
> - CONTEXTPATH

- 프로퍼티 타입 컨버전
> - @DurationUnit
- 프로퍼티 값 검증
> - @Validated
> - JSR-303 (@NotNull, ...) 
- @Value
> - SpEL 을 사용할 수 있지만...
> - 위에 있는 기능들은 전부 사용 못합니다.
