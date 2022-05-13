package com.smyunis.halite.domain;

import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

import java.time.LocalDateTime;

public abstract class DomainEvent {
    private LocalDateTime eventTimeStamp = LocalDateTime.now();

    public LocalDateTime getEventTimeStamp() {
        return eventTimeStamp;
    }

}
