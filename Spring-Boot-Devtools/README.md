# Spring-Boot-Devtools
- 캐시때문에 바뀐 내용이 바로 브라우저에 적용이 안될 경우가 있는데 캐시설정을 개발환경에 맞게 변경.
- 클래스패스에 있는 파일이 변경 될 때마다 자동으로 재시작
> - 직접 껐다 켜는거 (cold starts)보다 빠른다. 왜?
> - 릴로딩 보다는 느리다. (JRebel 같은건 아님)
> - 리스타트 하고 싶지 않은 리소스는? spring.devtools.restart.exclude
> - 리스타트 기능 끄려면? spring.devtools.restart.enabled = false
- 라이브 릴로드? 리스타트 했을 때 브라우저 자동 리프레시 하는 기능
> - 브라우저 플러그인 설치해야 함.
> - 라이브 릴로드 서버 끄려면? spring.devtools.liveload.enabled = false
- 글로벌 설정
> - ~/.spring-boot-devtools.properties
- 리모트 애플리케이션