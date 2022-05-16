package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;

public class CateringEvent {
    private final CateringEventData data;

    public CateringEvent(CateringEventData data) {
        this.data = data;
    }

    public void closeCateringEvent() {
        assertEventIsOpen();
        data.setStatus(CateringEventStatus.CLOSED);
    }

    private void assertEventIsOpen() {
        if(data.getStatus() != CateringEventStatus.OPEN)
            throw new InvalidOperationException("Can Not Close an Event which was not Open");
    }
}
