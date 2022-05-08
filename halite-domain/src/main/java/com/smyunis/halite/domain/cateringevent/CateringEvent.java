package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.cateringeventhost.domainevents.CateringEventUpdatedEvent;

import java.util.List;

public class CateringEvent {
    private CateringEventId id = new CateringEventId();
    private NumberOfAttendees expectedNumberOfAttendees;
    private Venue venue;
    private List<Caterer> caterers;
    private CateringEventStatus status = CateringEventStatus.Open;
    private String description;

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

    public class DomainEventHandler {
        public void handleCateringEventUpdated(CateringEventUpdatedEvent event){
            CateringEvent updatedCateringEvent = event.getUpdatedCateringEvent();
            expectedNumberOfAttendees = updatedCateringEvent.expectedNumberOfAttendees;
            venue = updatedCateringEvent.venue;
            caterers = updatedCateringEvent.caterers;
            status = updatedCateringEvent.status;
            description = updatedCateringEvent.description;
        }
    }
}
