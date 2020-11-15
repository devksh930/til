package me.devksh930.applicationeventpublisher.demo;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AnotherHandler {

    @EventListener
//    @Order(Ordered.HIGHEST_PRECEDENCE+2) //
    @Async
    public void handle(MyEvent myEvent) {
        System.out.println(Thread.currentThread().toString());
        System.out.println("anouther"+myEvent.getData());
    }
}
