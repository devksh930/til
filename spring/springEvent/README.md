# SPRING EVENT

[출처 https://www.baeldung.com/spring-events](https://www.baeldung.com/spring-events)

## 1. OverView

- 스프링 이벤트는 ```ApplicationContext```가 제공하는 기본적인 기능중 한가지다.

1. 이벤트는 ApplicationEvent 를 상속한다.

2. 퍼블러셔는 ApplicationEventPublisher 를 주입받는다.

3. 리스너는 ApplicationLister interface 를 구현한다.

## 2. Custom Event

- 스프링은 기본적으로 ```synchronous```한 CustomEvent를 만든다

#### 간단한 이벤트 만들기

```java
public class CustomSpringEvent extends ApplicationEvent {
    private String message;

    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
```

### publisher만들기

```java

@Component
public class CustomSpringEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomSpringEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishCustomEvent(final String message) {
        System.out.println("Publishing custom event.");
        CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}

```
- Publisher는 Listener들이 가져갈 수 있는 이벤트를 생산
-```ApplicationEvnetPublisherAware``` interface를 구현 하는것도 가능

#### Listener 만들기

```java

@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {
    @Override
    public void onApplicationEvent(CustomSpringEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
    }
}
```
- Spring Event는 기본적으로 동기식으로 처리된다 
- Spring Evnet는 기본적으 동기식으로 처리하는데 ```doStuffAndPublishAnEvent()```가 이벤트가 처리될 때 까지 리스너들을 block한다.