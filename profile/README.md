#Spring 프로파일
- 특정한 프로파일에서만 특정한 빈을 등록하고싶거나 애플리케이션의 동작을 특정 프로파일에는 조금 다르게 하고싶을때 썻다.
#### @Profile 
- @Configuration
- @Component
#### 어떤 프로파일을 활성화 할 것인가?
- spring.profile.active
#### 어떤 프로파일을 추가할 것인가?
- spring.profile.include
#### 프로파일용 프로퍼티
- application-{porfile}.properties
- application argument를 줘서 실행할수도 있다.