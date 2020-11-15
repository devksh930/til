Environment

EnvironmentCapable

- 프로파일 기능 : 빈들의 묶음 

프로파일 유즈케이스
-테스트 환경에서는 A빈을 사용 배포 환경에서는 B라는 빈을 사용하고 싶다
-이 빈은 모니터링 용도니깐 테스트할때는 필요가 없고 배포할때만 등록이 되면 좋겠다

프로파일 정의하기
-클래스에 정의학
 -@configuration @Profile(“test”)
-@Component @Profile(“test”)

프로팡리 표현식
! Not
& and
| or 
사용가능
