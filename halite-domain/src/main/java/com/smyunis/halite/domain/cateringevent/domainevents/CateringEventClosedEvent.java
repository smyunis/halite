package com.smyunis.halite.domain.cateringevent.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringevent.CateringEvent;

public class CateringEventClosedEvent extends DomainEvent {
    private final CateringEvent closedCateringEvent;

    public CateringEventClosedEvent(CateringEvent closedCateringEvent) {
        this.closedCateringEvent = closedCateringEvent;
    }

    public CateringEvent getClosedCateringEvent() {
        return closedCateringEvent;
    }
}
