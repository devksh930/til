## Application Context의 ResourceLoader

>ApplicationContext extends ResourceLoader
>Resource getResource(java.lang.String location)

### 리소스 읽어오기

- 파일 시스템에서 읽어오기
- 클래스패스에서 읽어오기
- URL로 읽어오기
- 상대/정대 경로로 읽어오기


##Resource 추상화

###특징 
- Java.net.URL을 추상화
 >- 기존에 있는 url클래스가 클래스 패스로 가저오는 기능이 없었다
 >- ServletContext를 기준으로 상대 경로로 읽어오는 기능 부재
 >- 새로운 핸들러를 등록하여 접미사를 만들어 사용할 수는 있지만 구현이 복잡하고 편의성 메소드가 부족하다
 >- 스프링 내부에서 많이 사용하는 인터페이스

###주요메소드
- getInputStrem()
- exist() 존재하는지
- IsOpen() 열려있는지
- getDescription() 전체경로 포함한 파일이름 또는 실제 URL

### 구현체
- UrlResource : java.net.URL 참고 기본으로 지원하는 프로토콜 http. Https,ftp, file, jar
- ClassPathResource : 지원하는 접두어 class path
- FileSystemResource
- ServletContextResource : 웹어플리케이션 루트에서 상대 경로로 리소스를 찾는다(사실상 가장많이 쓰임))

### 리소스 읽어오기
- Resource의 타입은 location 문자열과 ApplicationContext의 타입에 따라 결정된다
> - ClassPathXmlApplicationContext —> ClassPathResource
> - FileSystemXmlApplicationContext —> FileSystemResource
> - WebApplicationContext —> ServletContextResource

### ApplicationContext의 타입에 상관없이 리소스 타입을 강제하려면 java.net.URL 접두어(+classpath:)중 하나를 사용할수 있다
####(명시적인 방법이므로 추천)
- Classpath:me/devksh930/config.xml -> ClassPathResource
- File://some/resource/path/config.xml -> FileSystemResource
