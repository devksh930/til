# SPRING-MVC CORS
### CORS(Cross - Origin - Resource - Share)
- 서로 다른 Origin에서 요청 가능함
- 기본은 SOP가 적용 되있다

- Single-Origin Pollicy
> - 같은 Origin에만 요청을 보낼수 있다.
- 웹브라우저가 지원하는 표준 기술

### Origin?
- URI 스키마 (http, https)
- hostname (devksh930.me, localhost)
- 포트 (8080, 18080)

### SPRING MVC @CrossOrigin
- @CrossOrigin(ogrigins = "http://localhost:18080")
- 메서드나 컨트롤러에 걸처서 설정이 가능하며 해당 Origin이 접근이 가능할수 있도록 해줌
- 전역적인 설정이 필요할경우
<pre><code>
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/hello")
                .allowedOrigins("http://localhost:18080");
    }
}
</code></pre>
- WebMvcConfigurer를 사용하여 구현 할수 있다.

