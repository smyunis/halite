package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;

import java.util.List;

public class CateringEvent {
    private CateringEventId id = new CateringEventId();
    private NumberOfAttendees expectedNumberOfAttendees;
    private Venue venue;
    private CateringEventHostId cateringEventHostId;

    private List<Caterer> caterers;


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

    public void assignCateringEventHost(CateringEventHostId cateringEventHostId) {
        this.cateringEventHostId = cateringEventHostId;
    }


}
