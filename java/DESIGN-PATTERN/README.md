 <H1>JAVA 디자인 패턴</H1>


<H3>인터페이스(Interface)</H3>

- 기능에 대한 [선언](./src/com/devksh930/IF_Deligate/InterFace/Ainterface.java)과 [구현](./src/com/devksh930/IF_Deligate/InterFace/AinterfaceImpl.java)분리
- 기능의 [사용통로](./src/com/devksh930/IF_Deligate/InterfaceMain.java)


<H3>위임(deligate)</H3>

- 어떤기능을 구현할때 책임을 [다른 객체에게 넘기는 것](./src/com/devksh930/IF_Deligate/deligate/Aobj.java)

<H3>전략(strategy)</H3>

- 기능을 구현할때 어떠한 알고리즘([칼](./src/com/devksh930/Strategy/Knife.java), [검](./src/com/devksh930/Strategy/Sword.java))등을 추상 클래스로 구현하고 각각의 알고리즘을 필요할때 [교환](./src/com/devksh930/Strategy/StrategyMain.java)해서 사용 할수 있도록 만든다


<H3>어댑터 패턴(Adapter)</H3>

- 연관성 없는 두 객체를 묶어서 사용한다.
- 미리 주어진 함수를 요구사항에 맞춰 변경해서 사용할수 있다.
- 구현된 객체의 반환읜 [double](./src/com/devksh930/Adapter/Math.java)로 한다 하지만 요구사항은 [Float](./src/com/devksh930/Adapter/AdapterImpl.java)으로 반환되어야 한다


<H3>탬플릿 메소드 패턴(template method)</H3>

- 알고리즘의 [구조를 메소드에 정의](./src/com/devksh930/TemplateMethod/dp/AbstGameConnectHelper.java) 하고 하위 클래스에서 [알고리즘 구조의 변경없이 알고리즘](./src/com/devksh930/TemplateMethod/dp/DefaultGameConnectHelper.java)을 재정의 한다.
- 구현하려는 알고리즘의 일정한 프로세스가 있다 구현하려는 알고리즘이 변경 가능성이 있을경우 사용.

<H3>팩토 메소드 패턴(factory method)</H3>
정리필요