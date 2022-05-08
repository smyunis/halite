package com.smyunis.halite.domain;

import java.time.LocalDateTime;

public abstract class DomainEvent {
    private LocalDateTime eventTimeStamp = LocalDateTime.now();

    public LocalDateTime getEventTimeStamp() {
        return eventTimeStamp;
    }

}
