package me.devkh930.springaspect.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//클라이언트코
@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    EventService eventService;

    //인터페이스가 있는경우에는 인터페이스 타입으로 주입
    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventService.createEvent();
        eventService.publishEvent();
        eventService.deleteEvent();
    }
}
