package me.devksh930.springbootjsp;

import java.time.LocalDateTime;

public class Event {
    private String name;
    private LocalDateTime start;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }
}
