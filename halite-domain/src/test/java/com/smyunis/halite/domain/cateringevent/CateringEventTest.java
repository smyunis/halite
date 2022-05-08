package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.cateringeventhost.domainevents.CateringEventUpdatedEvent;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CateringEventTest {
    private CateringEvent cateringEvent;

    @BeforeEach
    void setup() {
        cateringEvent = new CateringEvent();
    }

    @Test
    void canGetAndSetId() {
        CateringEventId eventId = new CateringEventId("New-Evt-Id");

        cateringEvent.setId(eventId);
        CateringEventId id = cateringEvent.getId();

        assertEquals(eventId,id);
    }

    @Test
    void moreAtLeastOnePersonWillBeAttendingTheEvent () {

        int randomNoOfAttendees = new Random().nextInt(1,1000);
        var noOfExpectedAttendees = new NumberOfAttendees(randomNoOfAttendees);

        cateringEvent.setExpectedNumberOfAttendees(noOfExpectedAttendees);
        NumberOfAttendees noOfAttendees = cateringEvent.getExpectedNumberOfAttendees();

        assertEquals(noOfExpectedAttendees.numberOfAttendees(),noOfAttendees.numberOfAttendees());
        assertTrue(noOfAttendees.numberOfAttendees() >= 1);
        assertThrows(InvalidValueException.class,() -> {
            cateringEvent.setExpectedNumberOfAttendees(new NumberOfAttendees(-1));
            cateringEvent.setExpectedNumberOfAttendees(new NumberOfAttendees(0));
        });
    }

    @Test
    void canSetAndGetAVenueForTheEvent() {
        Venue moonVenue = new Venue("The Moon");
        cateringEvent.setVenue(moonVenue);
        Venue venue = cateringEvent.getVenue();

        assertEquals(moonVenue,venue);
    }

    @Test
    void canHandleCateringEventUpdatedEvent() {
        CateringEvent updatedCateringEvent = new CateringEvent();
        updatedCateringEvent.setDescription("This is a fancy wedding");

        cateringEvent.new DomainEventHandler()
                .handleCateringEventUpdated(new CateringEventUpdatedEvent(updatedCateringEvent));

        assertEquals("This is a fancy wedding",cateringEvent.getDescription());
    }


}
