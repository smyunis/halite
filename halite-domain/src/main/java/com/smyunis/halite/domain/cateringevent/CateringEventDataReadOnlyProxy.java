package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;

import java.time.LocalDateTime;

public class CateringEventDataReadOnlyProxy {
    private final CateringEventData data;

    public CateringEventDataReadOnlyProxy(CateringEventData data) {
        this.data = data;
    }
    
    public CateringEventId getId() {
        return data.getId();
    }

    public CateringEventHostId getCateringEventHostId() {
        return data.getCateringEventHostId();
    }

    public CateringEventStatus getStatus() {
        return data.getStatus();
    }

    public NumberOfAttendees getExpectedNumberOfAttendees() {
        return data.getExpectedNumberOfAttendees();
    }

    public Venue getVenue() {
        return data.getVenue();
    }

    public String getDescription() {
        return data.getDescription();
    }

    public LocalDateTime getEventStartTime() {
        return data.getEventStartTime();
    }

    public LocalDateTime getEventEndTime() {
        return data.getEventEndTime();
    }
}
