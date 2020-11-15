# 스프링 Rest클라이언트 커스터마이징

### RestTemplate
- 기본으로 java.net.HttpURLConnection 사용.
- 커스터마이징
- 로컬 커스터마이징
- 글로벌 커스터마이징
> - RestTemplateCustomizer
><pre><code>
>@Bean
>public RestTemplateCustomizer restTemplateCustomizer() {
>    return new RestTemplateCustomizer() {
>        @Override
>        public void customize(RestTemplate restTemplate) {
>            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
>        }
>    }
>}</code></pre>
> - @Bean 등록하여사용
 
### WebClient
- 기본으로 Reactor Netty의 HTTP 클라이언트 사용.
- 커스터마이징
- 로컬 커스터마이징
> <pre><code>WebClient webClient = build
>          .baseUrl("http://localhost:8080")
>          .build();</code></pre>
- 글로벌 커스터마이징
><pre><code>
> @Bean
> public WebClientCustomizer webClientCustomizer() {
>     return new WebClientCustomizer() {
>         @Override
>         public void customize(WebClient.Builder webClientBuilder) {
>             webClientBuilder.baseUrl("http://localhost:8080");
>         }
>     };
> }</code></pre>
> - @Bean으로 등록 하여 사용
