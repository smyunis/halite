package com.smyunis.halite.domain.cateringeventhost.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringevent.CateringEvent;

public class CateringEventUpdatedEvent extends DomainEvent {
    private final CateringEvent updatedCateringEvent;

    public CateringEventUpdatedEvent(CateringEvent updatedCateringEvent) {
        this.updatedCateringEvent = updatedCateringEvent;
    }

    public CateringEvent getUpdatedCateringEvent() {
        return updatedCateringEvent;
    }
}
