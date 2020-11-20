# 1 주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행되는 것인가?
### 목표
자바 소스 파일(.java)을 JVM으로 실행하는 과정 이해하기.
### 학습할것
- JVM이란 무엇인가
- 컴파일 하는 방법
- 실행하는 방법
- 바이트코드란 무엇인가
- JIT 컴파일러란 무엇이며 어떻게 동작하는지
- JVM 구성 요소
- JDK와 JRE의 차이


# JVM이란 무엇인가?

자바의 유명한 슬로건 중 하나 인 Write once, Run Everywhere(한번만 작성하고 어디서나 실행하세요.)처럼
자바는 어느 운영체제 상에서도 실행될수 있게 하고 jvm이라는 가상머신을 이용해서 간접적으로 접근하여 오브젝트가 필요해지지 않는 시점에 알아서 메모리 관리를 하게된다.
# 컴파일 하는 방법
- CLI로 컴파일 하기(mac zsh)기준)
본인이 사용하는 환경이 mac,zsh 이므로 썼으나 윈도우나 리눅스의 경우도 비슷하다고 생각한다.
1. 우선 jdk가 설치되어있고 환경변수가 등록 되어 있어야한다.
![](https://images.velog.io/images/devksh930/post/866e504d-c35c-4618-9b91-306bcba1cbad/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-18%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%203.17.51.png)
2. 간단한 코드작성
![](https://images.velog.io/images/devksh930/post/1744158f-9f55-4b0a-8981-2bf577694a61/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-18%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%203.21.16.png)
3. 컴파일
![](https://images.velog.io/images/devksh930/post/941b3875-a19a-400b-9509-a1edda961798/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-18%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%203.49.16.png)
javac를 하게되면 컴파일을 하여 class(ByteCode) 파일이 만들어 진다.
# 실행하는 방법
- java `classfile`을 입력하면 아래 처럼 실행이 가능하다.
![](https://images.velog.io/images/devksh930/post/1a0ab463-515b-4756-b430-dbad784af1ac/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-18%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%203.50.38.png)
# 바이트코드란 무엇인가
`BYTECODE`는 자바가상머신이 이해할수 있는 언어로 변환된 자바 소스코드를 의미한다.
컴파일 타임 환경에서 java소스코드를 자바컴파일러가 `BYTECODE`를 생성하게 된다.
class파일만 있으면 어떤 환경에서든 실행가능하다.

java컴파일->Byte코드생성(.class파일)->JVM에 의해서 기계어로 번역

# JIT 컴파일러란 무엇이며 어떻게 동작하는지
Java 코드는 자바컴파일러를 통해 ByteCode로 변환이 된다. 그리고 ByteCode는 다시 기계어로 번역이 된다. 자바 컴파일러가 Byte코드 변환 -> jvm에 의해서 기계어로 번역 하는 과정이 많고 이과정에 의해 작업 비용이 많이 든다. 이러한 단점을 극복 하기 위해 JIT방식이 나오게 되었다.
JIT컴파일러는 같은 코드를 매번 해석하는 대신 처음 실행될때 인터프리트를 하면서 자주 쓰이는 코드를 캐싱한뒤 이후에 캐싱된 코드를 가져다 쓰기 때문에 인터프리터의 느린실행 속도를 개선할 수있다.
# JVM 구성요소
jvm은`Class Loader`, `Garbage Collector`,`Execution Engine`,`Runtime Data Area` 4가지 구성요소를 가지고있다.
#### `Class Loader`
 `Class Loader`은 자바클래스를 JVM으로 동적로드 하는 JRE의 일부 이다.
 `Class Loader`는 라이브러리를 위치시키고 내용물을 읽으며 라이브러리들 안에 포함된 클래스들을 읽는 역할을 한다. 로딩은 일반적으로 요청이 오면 이루어지며 클래스가 프로그램에 의해 호출될때 까지 로드하지 않는다.
 **명명된 클래스는 주어진 클래스로더에 의해 한 번만 로드될 수 있다.**
#### `Execution Engine`
 `Execution Engine`은 `Class Loader`에 의해 JVM으로 로드된 Class파일(ByteCode)들은 `Runtime Data Area`의 Method Area에 배치되는데 JVM은 Method Area의 바이트 코드를 `Excution Engine`에 제공하여 Class에 정의된 내용대로 ByteCode를 실행 시킨다 이 때 **Load된 파이트 코드를 실행하는 Runtime Module이 Execution Engine(실행엔진) 이다.**
#### `Runtime Data Areas`
`Runtime Data Areas`는 Process로서의 JVM이 프로그램을 수행하기 위해 OS로부터 할당 받는 메모리 영역이다.
-`Runtime Data Areas`의 메모리 영역
   - PC Registers
   - Java Virtual Machine Stacks
   - Native Method Stacks
   - Method Area
   - Heap
- PC Register와 두 개의 Stack은 Thread 별로 생성이 되고, Method Area와 Heap은 모든 Thread에 공유된다.
#### `Garbage Collector`
 자바는 메모리관리를 사용자가 아닌 JVM이 알아서 해준다.
 jvm의 GC에서 메모리 정리는 따로 쓰레드로 동작하고있다.
 - Heap내의 객체 중엣 garbage를 찾아낸다
 - 찾아낸 garbage를 처리해서 힙의 메모리를 회수한다.
 
# JDK와 JRE의 차이
![](https://images.velog.io/images/devksh930/post/839a6edb-dea9-47ab-8c51-5004f100e1cf/img1.daumcdn.png)
`JRE`는 `Java Runtime Environment`의 약자 이다.
- `JRE`는 `JVM`이 자바 프로그램을 동작시킬 때 필요한 라이브러리 파일들과 기타 파일들을 가징고 있다. 

`JDK`는 `Java Development Kit`의 약자 이다.
- `JDK`는 `JRE`+ 개발에 필요한 Javac,java등을 포함한다.

### 참고자료
> https://www.itworld.co.kr/news/110837
>https://yaboong.github.io/java/2018/06/09/java-garbage-collection/
>https://medium.com/@lazysoul/jit-just-in-time-16bb63f3ae26
>https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_%ED%81%B4%EB%9E%98%EC%8A%A4%EB%A1%9C%EB%8D%94
>https://www.slipp.net/wiki/pages/viewpage.action?pageId=8880270

