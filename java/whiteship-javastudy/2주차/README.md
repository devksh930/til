# 목표
자바의 프리미티브 타입, 변수 그리고 배열을 사용하는 방법을 익힙니다.

# 학습할 것
- 프리미티브 타입 종류와 값의 범위 그리고 기본 값
- 프리미티브 타입과 레퍼런스 타입
- 리터럴
- 변수 선언 및 초기화하는 방법
- 변수의 스코프와 라이프타임
- 타입 변환, 캐스팅 그리고 타입 프로모션
- 1차 및 2차 배열 선언하기
- 타입 추론, var

## 프리미티브 타입 종류와 값의 범위 그리고 기본 값
`PrimitiveType`은 stack메모리에 값이 존재하며
자바에서는 8가지의 기본데이터형을 지원한다
- 프리미티브 타입 종류,크기,범위,기본값
![](https://images.velog.io/images/devksh930/post/9d25010a-cfd7-4503-9af7-65f657f0f99f/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-19%20%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB%2011.53.18.png)

## 프리미티브 타입과 레퍼런스 타입
`PrimitiveType`은 stack메모리에 값이 존재한다. 반면에 `ReferenceType`은 하나의 `인스턴스`이며
`스택`메모리에는 참조값만 있고 실제 값은 `힘메모리`에 존재한다. 그리고 값을 필요로 할 때마다 언박싱 과정을 거쳐야한다.
- Primitive Type : byte, short, int, long, float, double, boolean, char
- Reference Type : 기본형을 제외한 모든자료형은 Reference Type이다.

## 리터럴
리터럴은 프로그램에서 직접 표현한 값을 리터럴이라고 한다
```java
   		int a = 1;
        int b = 2;
        int c = 1;

        System.out.println("a 해쉬코드"+System.identityHashCode(a));
        System.out.println("b 해쉬코드"+System.identityHashCode(b));
        System.out.println("c 해쉬코드"+System.identityHashCode(c));
```
- 여기서 보면 `int a = 1`이라고 되있는 1 이 리터럴 이다.
- 리터럴은 한 번 저장을 하고 나면 다음부터는 재사용을 한다.
- 소스 코드를 보면 1이라는 데이터가 저장되어 있는지 확인하고 저장이 되어있다면
- 변수나 상수가 메모리에 할당된 공간이라고하면 리터럴은 이에 **저장되는 값**이라고 할수있다.
![](https://images.velog.io/images/devksh930/post/18883872-341c-4783-9abd-7536c46e26d0/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-19%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2012.44.33.png)
해쉬 코드를 보면 a의 1이란값이 c의 1이란 값과 같은 해쉬코드를 가지는 것을 볼수 있다.
이걸로 리터럴은 같은값이 존재하면 재사용 되는것을 볼수있다.
> #### 주요원시 타입의 리터럴은 다음과 같다
>정수
>byte ( 1byte) : 10, 20, 30...
>short ( 2byte) : 10, 20, 30...
>int (4 byte) : 10, 20, 30...
>long(8 byte) : 10L, 20L, 30L...
>
>실수
>double(8 byte) : 1.2, 10.34, 5.6...
>float(4 byte) : 1.2F, 10.34F, 5.6F...

## 변수 선언 및 초기화하는 방법
#### 변수 선언
변수 선언은 변수를 사용하기위해 저장공간을 확보 하겠다는 뜻이다
```java
int a;
```
위 코드는 int라는 변수를 사용하기전에 a라는 변수를 int(4Byte)만큼의 메모리를 할당하겠다는 뜻이다.
#### 초기화
변수 초기화는 선언된 변수에 값을 할당하기 위해 사용한다. 해당 변수를 사용하기 위해서는 초기화되어 있어야 한다.
```java
int a = 1;
```
위 코드는 int라는 변수를 사용하기전에 a라는 변수를 int(4Byte)만큼의 메모리를 할당하고 1이란 값으로 초기화를 하겠다는 뜻이다.


## 변수의 스코프와 라이프타임
#### 스코프
변수와 메서드 같은 것들을 사용할수 있는 것은 **이름**이 있기 때문이다.
프로그램이 커지면 여러가지 이유로 **이름**이 충돌하게 된다. 이를 해결하기 위해 고안된것이 **Scope**이다

```java
public class test { 
	int iv; // 인스턴스 변수
        static int cv; // 클래스 변수 
        void method() { 
        	int lv; // 지역 변수 
        } 
}
```
- 인스턴스 변수(Instance Variables)
     - 클래스 전체(static제외)
- 클래스 변수(Class Variables)
     - 클래스 전체
- 지역 변수(Local Variables)
     - 선언된 블록 내
#### 라이프타임
- 인스턴스 변수(Instance Variables)
    - 객체가 메모리에 머무를때 까지
- 클래스 변수(Class Variables)
    - 프로그램의 수명이 끝날때까지 또는 클래스가 메모리에 로드 되는 한
- 지역 변수(Local Variables)
    - 선언된 블록을 벗어날때까지
 
 >출처
 https://www.tutorialspoint.com/scope-and-lifetime-of-variables-in-java
## 타입 변환, 캐스팅 그리고 타입 프로모션
자바에는 두가지 type 변환이 있다.
- 강제 타입 변환(Casting) : 명시적
- 자동 타입 변환(Promotion) : 묵시적
#### Casting
큰 크기의 타입을 작은 타입으로 자동 타입 변환이 불가능하다.
### Type Promotion
`Promotion`은 프로그램 실행도중 자동으로 일어난다.
작은 메모리 크기의 데이터타입이 큰 메모리 크기의 데이터 타입으로 변환하는 행위를 말한다.
```java
byte a = 10; //정수 10을 byte타입의 변수인 a에 저장
int b = a;  //byte 타입의 변수 a를 int 타입의 변수 b에저장
```
위 예시 처럼 작은메모리 크기의 데이터 타입에서 큰 크기의 데이터타입으로 값을 저장하면 별다른 문법없이 형변환이 발생한다. 이러한 변환을 자동 `Promotion`(자동타입변환)이라고 한다.

- 형변환 우선 순위
`byte(1byte)` < `short(2byte)` < `int(4byte)` < `long(8byte)` < `float(4byte)` < `double(8byte)`
위의 순서대로 형변환이 일어난다.
> **long이 어떻게 float로 자동 형변환이 가능할까?**
> long은 8Byte이고 float는 4Byte이다. 하지만 자바에서 자동형변환을 지원한다.
> 이는 java에서 float가 값을 지수 형태로 저장하기 때문에 `long(8byte)`에서 `float(4byte)`>로의 변환은 묵시적이다. 
> 2345678이 float에 저장된다면 2.345678E6(E6의 값은 10의 6제곱을 의미한다.)
> 만약 float에 저장할수 없는 긴값을 저장하면 JVM은 그것을 지수 형태로 변환한 다음 저장한다.
>추가적으로 JVM은 정밀도 손실에 대해 신경쓰지 않는다.

>출처
How long to float is implicit conversion in Java?
- https://acmecoding.wordpress.com/2017/01/01/how-long-to-float-is-implicit-conversion-in-java/


## 1차 및 2차 배열 선언하기
#### 배열
모든 타입에 대응하는 배열이 존재한다. 정수형 배열은 `int[] 타입` String 객체의 배열은 `String []` 타입이 된다.
- 배열 할당
String타입의 배열을 저장할수 잇는 변수
```java
String[] names;
```
- 배열 초기화
새로운 배열을 초기화 하려면 new 연산자가 필요하다.
```java
names = new String[100]
```
names의 요소는 100개로 구성되며 각 요소는 names[0]...names[99]로 접근이 가능하다.
> names[-1]나 names[100]처럼 없는 요소에 접근시 `ArrayIndexOutOfBoundsException`이 발생한다

- new 연산자로 배열 선언 하면 배열을 기본 값으로 채운다.
    - 숫자타입(char포함)의 배열은 0으로 채운다.
    - boolean의 배열은 false로 채운다
    - 객체의 배열은 null 참조로 채운다.
- 배열의 복사
```java
 int[] arrayA = new int[4];
 int[] arrayB = arrayA;
  ```
  ![](https://images.velog.io/images/devksh930/post/28f6bdaf-8807-417a-b3d8-ec2e3cda2988/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-21%20%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB%203.08.47.png)
 arrayA와 arrayB의 해쉬코드를 보면 같은걸 볼수 있다.
 ![](https://images.velog.io/images/devksh930/post/1157f3fa-8f80-420a-b718-345dd18adf82/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-21%20%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB%203.09.11.png)
![](https://images.velog.io/images/devksh930/post/93654b60-cd34-42d1-b5c2-f8092f440064/image.png)
배열변수를 또 다른 배열 변수로 복사 할수 있다. 그러면 두변수가 같은 배열을 참조 하게된다
이러한 공유를 원하지 않으면 배열의 사본을 만들어야한다.
```java
int[] arrayB = Arrays.copOf(arrayA, arrayA.length);
```
이 메서드는 새 배열을 원하는 길이로 생성하고 원본배열의 요소를 복사한다.
#### 2차원 배열
2차원 배열은 다차원 배열은 진정한 의미의 다차원 배열이 아니다. 배열의 배열로 다차원 배열을 구현한다.
> 그래서 자바는 행마다 길이가 다른 가변배열을 만들수 있다.

```java
    int[][] multi ={
                {1,2,3},
                {5,6,7},
                {8,9,10}
        };
```
아래와 같이 기술적으로 int[]로 구성된 1차원 배열이다.
![](https://images.velog.io/images/devksh930/post/bd2c8343-08ac-4de3-b21c-388060f9c623/image.png)
- 가변 배열
자바는 배열의 배열로 다차원 배열을 구현하므로 가변배열도 가능하다.
```java
    int[][] dynamic = {
                {1, 2},
                {1, 2, 3, 4},
                {1}
        };
        System.out.println(dynamic[0].length);
        System.out.println(dynamic[1].length);
        System.out.println(dynamic[2].length);
    }
```
- 출력결과
![](https://images.velog.io/images/devksh930/post/7557001c-ddda-45ec-a5e5-72bea31513ba/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-21%20%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB%203.24.11.png)

## 타입 추론, var
#### 타입추론
타입추론이란 데이터 타입을 명시하지 않아도 컴파일러가 데이터타입이 무엇인지 추론한다는 의미이다.
자바5의 `Genric`, 8전의 `lamda`에서 타입 추론이 사용된다. 
#### var
java 10부터 지원하는 구문이다.
선언과 동시에 초기화를 필수로 요구하며 컴파일러가 초기화 값을 통해 타입을 유추한다;.
```java
        var a = "이타입은?";
        var b = new ArrayList<>();
        var c = new HashMap<>();
        var d = 1;
        var e = 1.2;
        Typetester t = new Typetester();
        System.out.println(a.getClass().getName());
        System.out.println(b.getClass().getName());
        System.out.println(c.getClass().getName());
        t.printType(d);
        t.printType(e);
```
>`Typetester`는 primitive타입의 변수를 체크 하기위해 만듬
> 출처: https://stackoverflow.com/questions/3993982/how-to-check-type-of-variable-in-java

![](https://images.velog.io/images/devksh930/post/46201425-ae08-49f4-b639-240ebf2c25d9/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202020-11-21%20%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB%203.42.41.png)

해당코드의 출력 결과를 보면 a,b,c같이 다이아몬드 연산자를 사용하면 해당 Object 타입으로 유추하며 d,e 같은 정수나 실수는 원시값으로 타입을 유추하는 것을 볼수있다 이는 컴파일러가 초기화 값을 통해 타입을 유추하는것을 알수있다.

# 출처
>https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
>https://www.w3schools.com/java/java_data_types.asp