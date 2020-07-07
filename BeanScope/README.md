<h1>Bean scope</h1>

<h3>Prototype </h3>
- 매번 새로운 객체를 새로운 인스턴스를 만들어서 써야하는 scope

>프로토타입 빈이 싱글톤 빈을 참조하면 문제가 되지 않음
>>프로토타입 빈은 항상 새로운 인스턴스이겠지만 프로토타입이 참조하는 싱글톤빈은 매번 동일해서 문제가 되지않음


<h3>Singletone</h3> 
- 유일한 인스턴스를 지닌다. 동시성 문제가 발생할수도 있어서 Three Safe 한 방법의 코딩을 해야한다 applicationcontext가 만들때 만들어진다 구동할때 시간이 좀더 걸릴수있음

>싱글톤 타입의 빈이 프로토타입을 참조하게 되는 경우에는 문제가 발생
>>싱글톤스코프의 빈이 프로토타입의 빈을 참조 하게되면 생성할때 프로토타입빈의 프로퍼티도 이미 세팅이 된다 쓰려고 하면 쓰려고 할때 프로퍼티의 값이 변경이 되지않음 그게 문제

<h3> 해결방안</h3>  

- <h4>proxyMode</h4>
<pre><code>@Component @Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
</code></pre>
><p>proxyMode-> prototype빈을 proxy로 감싸라</p><p>ScopedProxyMode.TARGET_CLASS ->class 기반으로</p>

매번 프로토타입빈을 바꿔줘야 하는데 proxy를 사용하지 않으면 바꿔질 여지가 없다 
###### jdk는 인터페이스기반의 프록시밖에 못만듬스 써드파 cg라이브러리를 이용함 cg라이브러리는 class기반의 프록시로 만들어준다 
###### TARGET_INTERFACE를 하게 되면 jdk를 이용하여 해당 인터페이스를 프록시로 감싼 

★실질적으로 prototype 객채를 주입하는것이 아닌  prototype 빈을 감싸고 있는 proxy를 주입함

<h3>Object-Provider이용</h3>
- [Object-provider](./src/main/java/me/devksh930/beanscope/demo/Single.java) 이용
