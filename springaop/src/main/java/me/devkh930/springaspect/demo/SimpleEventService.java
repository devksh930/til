package me.devkh930.springaspect.demo;

import org.springframework.stereotype.Service;

@Service
public class SimpleEventService implements EventService {
    @PerfLogging
    @Override
    public void createEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Create an event");
    }

    @PerfLogging
    @Override
    public void publishEvent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Publish an event");
    }

    @Override
    public void deleteEvent() {
        System.out.println("Delete an event");
    }
}
