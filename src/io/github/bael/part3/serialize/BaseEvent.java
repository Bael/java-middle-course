package io.github.bael.part3.serialize;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BaseEvent {
    @Override
    public String toString() {
        return "BaseEvent{" +
                "createdOn=" + createdOn +
                '}';
    }

    protected LocalDateTime createdOn;

    {
        System.out.println("Base Event init block called!");
    }
    public BaseEvent() {
        System.out.println("Base Event constructor called!");

    }
}
