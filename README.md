<H1>JAVA 디자인 패턴</H1>


<H3>인터페이스(Interface)</H3>

- 기능에 대한 [선언](./src/com/devksh930/IF_Deligate/InterFace/Ainterface.java)과 [구현](./src/com/devksh930/IF_Deligate/InterFace/AinterfaceImpl.java)분리
- 기능의 [사용통로](./src/com/devksh930/IF_Deligate/InterfaceMain.java)


<H3>위임(deligate)</H3>

- 어떤기능을 구현할때 책임을 [다른 객체에게 넘기는 것](./src/com/devksh930/IF_Deligate/deligate/Aobj.java)

<H3>전략(strategy)</H3>
- 기능을 구현할때 어떠한 알고리즘([칼](./src/com/devksh930/Strategy/Knife.java), [검](./src/com/devksh930/Strategy/Sword.java))등을 추상 클래스로 구현하고 각각의 알고리즘을 필요할때 [교환](./src/com/devksh930/Strategy/StrategyMain.java)해서 사용 할수 있도록 만든다