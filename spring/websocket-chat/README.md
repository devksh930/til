# 스프링 웹소켓

- 스프링 웹소캣을 활용한 채팅 demo 어플리케이션

#### WebSocket

- full-duplex(양방향)
- bi-direction(전이중적)
- persistent connection(지속적인연결)
- WebSocket이 탄생하기 이전에는 HTTP를 통해 양반향 통신을 하기 위해` poling`, `long pooling`,` http streaming`과 같은 방식을 사용했다
- 위 방식으로도 원하는 데이터를 클라이언트와 서버간 주고 받는데는 전혀 문제가 없다. 하지만 HTTP 프로토콜의 특성의 `request` `response`의 지속적인 수행, 그리고 그에
  따른 `http-header`의 중복적인 패킷 전달로 인해 속도저하 및 오버헤드는 근본적으로 문제가 발생한다.

#### WebSocket과 TCP의 차이점

- 웹소켓은 연결 요청에 대해 http를 통해 switching 및 Handshaking이 이뤄진다.
- TCP는 Binary 데이터만 주고 받을수 있지만 WebSocket은 Binary데이터 뿐만 아니라 Text데이터를 주고 받을수 있다.
- 핵심은 웹브라우저 환경에서의 연결지향 통신을 하기위한 기술이다.

#### Spring WebSocket
- Spring Websocket은 spring 4.0부터 지원하면 Maven3.2+, gradle4+, jdk8이상이 필요하다

- Spring에서는 2가지 방식으로 Websocket을 구현이 가능하다.
  - WebSocket 데이터를 직접처리
  - Stomp 프로토콜을 이용하여 메세징 처리