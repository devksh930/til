# DispatcherServlet 동작원리
DispatcherServlet 초기화
- 다음의 특별한 타입의 빈들을 찾거나 기본 전략에 해당하는 빈들을 등록한다.
- HandlerMapping : 핸들러를 찾아주 인터페이스
- HandlerAdapter : 핸들러를 실행하는 인터페이스 
- HandleExceptionResolver
- ViewResolver
- ....

DispatcherServlet 동작 순서
1. 요청분석(로케일, 테마, 멀티파트 등)
2. (핸들러 맵핑에 위임하여) 요청을 처리할 핸들러를 찾는다.
3. (등록되어 있는 핸들러 어댑터 중에)해당 핸들러를 실행할 수 있는 "핸들러어댑터"를 찾는다
4. 찾아낸 "핸들러 어댑터"를 사용해서 핸들러의 응답을 처리한다.
> - 뷰 이름에 해당하는 뷰를 찾아서 모델 데이터를 렌더링한다
> - @ResponseEntity가 있다면 Converter를 사용해서 응답 본문을 만들고
5. (부가적으로)예외가 발생했다면 예외 처리 핸들러에 요청 처리를 위임한다.
6. 핸들러에 리턴값을 보고 어떻게 처리할지 판단한다.
7. 최종적으로 응답을 보낸다
# 커스텀 ViewResolver
ViewResolver : ViewResolver타입의 빈들을 찾아와서 Viresolver목록에 넣어둠 없다면 기본전략을 가져옴 (기본전략에는 InternalViewResolver가 들어 있다. )
- InternalResourceViewResolver
> - Prefix
> - Sufix
