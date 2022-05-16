package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

import java.time.LocalDateTime;

public class CateringEventData {
    private CateringEventId id = new CateringEventId();
    private CateringEventHostId cateringEventHostId;
    private NumberOfAttendees expectedNumberOfAttendees;
    private Venue venue;
    private CateringEventStatus status = CateringEventStatus.OPEN;
    private String description;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;

    public CateringEventId getId() {
        return id;
    }

    public CateringEventData setId(CateringEventId id) {
        this.id = id;
        return this;
    }

    public CateringEventHostId getCateringEventHostId() {
        return cateringEventHostId;
    }

    public CateringEventData setCateringEventHostId(CateringEventHostId cateringEventHostId) {
        this.cateringEventHostId = cateringEventHostId;
        return this;
    }

    public NumberOfAttendees getExpectedNumberOfAttendees() {
        return expectedNumberOfAttendees;
    }

    public CateringEventData setExpectedNumberOfAttendees(NumberOfAttendees expectedNumberOfAttendees) {
        this.expectedNumberOfAttendees = expectedNumberOfAttendees;
        return this;
    }

    public Venue getVenue() {
        return venue;
    }

    public CateringEventData setVenue(Venue venue) {
        this.venue = venue;
        return this;
    }

    public CateringEventStatus getStatus() {
        return status;
    }

    public CateringEventData setStatus(CateringEventStatus status) {
        this.status = status;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CateringEventData setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public CateringEventData setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
        return this;
    }

    public LocalDateTime getEventEndTime() {
        return eventEndTime;
    }

    public CateringEventData setEventEndTime(LocalDateTime eventEndTime) {
        assertEventEndTimeIsAfterStartTime(eventEndTime);
        this.eventEndTime = eventEndTime;
        return this;
    }

    private void assertEventEndTimeIsAfterStartTime(LocalDateTime eventEndTime) {
        if (this.eventStartTime.isAfter(eventEndTime))
            throw new InvalidValueException("End Time Can Not be before Start Time");
    }
}
