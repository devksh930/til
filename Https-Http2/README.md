# 내장 웹서버 응용 HTTPS와 HTTP

##HTTPS
 - keystore 만들기
 <pre>
 <code>
 keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keysotre.p12 -validity 4000
 </code>
 </pre>
 - application.properties 설정
<pre>
<code>server.ssl.key-store=keystore.p12
server.ssl.key-store-password=123456
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=tomcat</code></pre>
>https를 적용하고 나면 http를 더이상 사용 하지 못한다.
>http connector는 하나 이다. 그걸 https바꿔서 사용하는거라 http를 받을수 있는 connector가 존재하지 않는다.
>> ## 해결방안
>> <pre>
>> <code>
>>   @Bean
>>    public ServletWebServerFactory servletWebServerFactory() {
>>        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
>>        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(createStandardConnector());
>>        return tomcatServletWebServerFactory;
>>    }
>>    private Connector createStandardConnector() {
>>        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
>>        connector.setPort(8080);
>>        return connector;
>>    }
>> </code>
>> </pre>
>> - 해당코드를 추가하여 connector를 하나 추가한다.

## HTTP2
- 사용방법(application.properties)  
<pre><code>server.http2.enabled=true</code></pre>
* Undertow는 1.4.0버전이상 jdk8 이상이면 해당코드만 설정하면 다른 설정이 필요 없음
* Jetty는 9.4.8부터 Conscrypt라이브러리에서 HTTP2도 지원가능하다.
> 의존성 필요
> - org.eclipse.jetty:jetty-alpn-conscrypt-server
>- org.eclipse.jetty:jetty-alpn-conscrypt-server
* Tomcat은 jdk9와 tomcat9.0이상이면 해당 porperty를 설정하면 다른설정이 필요하지 않다.
>> tomcat의 [이외 버전들](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-configure-http2)
