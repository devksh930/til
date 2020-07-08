###Application context 의 다른기능 MessageSource

- 국제화 i18n 기능

- 메세지를 다국화 하는 기능
<pre>
<code>ApplicationContext extends MessageSource
getMEssage(String code, Object[] args, String, default,Locale,loc)
</code>
</pre>

>기본적으로 ResourceBundleMessageSource 빈으로 등록이 되어있다
이 빈이 messages라는 리소스번들을 읽게 되어 있다

- 릴로딩기능이 있는 메시지소스
>실행중 빌드만 해주면 메세지를 바꿔서 출력 할수도 있다.