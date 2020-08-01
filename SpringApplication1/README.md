##SpringApplication

- 기본 로그 레벨 INFO
> vm 옵션 -Ddebug 또는 환경변수에 Debug  
- 배너
- banner.txt | gif | jpg | png
> classpath 또는 spring.banner.location
> ${spring-boot.version} 등의 변수를 사용할 수 있음.
> Banner 클래스 구현하고 SpringApplication.setBanner()로 설정 가능.
> - ### 배너끄는방법
>  SpringApplicationBuilder로 빌더 패턴 사용 가능