package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.order.OrderId;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class CateringEvent {
    private CateringEventId id = new CateringEventId();
    private NumberOfAttendees expectedNumberOfAttendees;
    private Venue venue;
    private CateringEventStatus status = CateringEventStatus.Open;
    private String description;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;

    private Set<OrderId> acceptedOrders = new HashSet<>();

    public CateringEventId getId() {
        return id;
    }

    void setId(CateringEventId id) {
        this.id = id;
    }

    public NumberOfAttendees getExpectedNumberOfAttendees() {
        return expectedNumberOfAttendees;
    }

    void setExpectedNumberOfAttendees(NumberOfAttendees expectedNumberOfAttendees) {
        this.expectedNumberOfAttendees = expectedNumberOfAttendees;
    }

    public Venue getVenue() {
        return venue;
    }

    void setVenue(Venue venue) {
        this.venue = venue;
    }

    public CateringEventStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    void setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public LocalDateTime getEventEndTime() {
        return eventEndTime;
    }

    void setEventEndTime(LocalDateTime eventEndTime) {
        if (this.eventStartTime.isAfter(eventEndTime))
            throw new InvalidValueException("End Time Can Not be before Start Time");
        this.eventEndTime = eventEndTime;
    }

    public Set<OrderId> getAcceptedOrders() {
        return acceptedOrders;
    }

    public void addAcceptedOrder(OrderId orderId) {
        acceptedOrders.add(orderId);
    }
}
