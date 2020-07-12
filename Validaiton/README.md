#Validation 추상화

- 애플리케이션에서 사용하는 객체 검증용 인터페이스

- 어떠한 계층과 상관없이 계층혁 아키텍처를 사용한다면 다사용가능


### boolean supports(Class<?> clazz)

- 인자로 넘어온 검증해야되는 인스턴스로 넘어온 클래스가 해당 validator가 검증할수 있는지 확인할수 있는 메소드 구현

### void validate(Object target, Errors errors)

- 실질적으로 검증작업이 일어나는 메소드
증
### Beanvalidation java ee 표준스펙

- bean에 있는 데이터를 검증할수 있는 기능

- SPRING BOOT 2.05이상 버전을 이용할 시에는 따로 validator 구현체를 작성하지 않고도 @Autowired라는 의존성 주입 어노테이션을 붙일 경우 LocalValidatorFactoryBean을 자동으로 등록한다.

- LocalValidatorFactoryBean에는 @NotEmpty, @Email등과 같은 Bean Validation을 체크하여 검
