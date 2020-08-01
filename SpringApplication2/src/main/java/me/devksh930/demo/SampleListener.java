package me.devksh930.demo;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        System.out.println("=======================");
        System.out.println("Application is Starting");
        System.out.println("=======================");
    }
}
//ApplicationStatingEvent는 applicationContext가 등록되기전에 실행이된다 하지만 이 listener는 applicationContext가 등록이되면서 @Component로 등록이 되서
// 작동하지않는다 직접 등록해줘야한다.