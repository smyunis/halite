package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringevent.domainevents.CateringEventClosedEvent;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CateringEvent {
    private final CateringEventData data;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public CateringEvent(CateringEventData data) {
        this.data = data;
    }

    public void closeCateringEvent() {
        assertEventIsOpen();
        data.setStatus(CateringEventStatus.CLOSED);
        domainEvents.add(new CateringEventClosedEvent(this));
    }

    private void assertEventIsOpen() {
        if(data.getStatus() != CateringEventStatus.OPEN)
            throw new InvalidOperationException("Can Not Close an Event which is not Open");
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public CateringEventId getId() {
        return data.getId();
    }

    public CateringEventDataReadOnlyProxy getDataReadOnlyProxy() {
        return new CateringEventDataReadOnlyProxy(data);
    }
}
