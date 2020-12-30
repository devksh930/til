package me.devksh930.demobootmvc;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String name;

    private int limitOfEnrollement;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
