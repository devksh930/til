package me.devksh930.demobootmvc;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class EventService {

    public List<Event> getEvent() {
        Event event1 = Event.builder()
                .name("Spring Mvc Study 1차")
                .limitOfEnrollement(5)
                .startDateTime(LocalDateTime.of(2019, 1, 10, 10, 0))
                .endDateTime(LocalDateTime.of(2019, 1, 10, 12, 0))
                .build();
        Event event2 = Event.builder()
                .name("Spring Mvc Study 2차")
                .limitOfEnrollement(5)
                .startDateTime(LocalDateTime.of(2019, 1, 17, 10, 0))
                .endDateTime(LocalDateTime.of(2019, 1, 27, 12, 0))
                .build();

        return List.of(event1, event2);
    }
}
