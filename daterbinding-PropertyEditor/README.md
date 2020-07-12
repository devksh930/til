# 데이터바인딩 추상화 : PropertyEditor

- 기술적인 관점 : 데이터바인딩이란 어떤 프로퍼티의 값을 타겟객체에 설정하는 기능
- 사용자관점: 사용자가 입력한 값을 애플리케이션 도메인 객체에다가 동적으로 할당하는 기능
 
> 할당할때 바인딩이 왜필요하냐면 사용자가 입력한값은 문자열이다 그 문자열을 객체마다 가지고 있는 다양한 property타입(int,long,Boolean,Date등 또는 도메인객체타입 자체)으로 변환해주는 기능

## org.springframework.validation.DataBinder
- 고전적인 방식의 데이터 바인딩

> ###EventEditor extends PropertyEditorSupport
><p>String getAsText()</p>
><p>void setAsText(String text)</p>

- Spring3.0 이전까지 DataBinder가 변환 작업을 위해 사용하던 인터페이스
- 쓰레드 세이프 하지 않다
>getValue, setValue는 PropertyEditor가 가지고 있는 값이다 이 값이 서로다른 쓰레드에게 공유가 가능하다. statefull하다 상태정보를 저장하고 있어서 쓰레드세이프 하지 않다. 구현체들은 여러쓰레드에 공유해서 사용하면 안된다. 
><p>예: bean으로 등록해서 사용할경우 심각한 문제가 발생한다.</p>

