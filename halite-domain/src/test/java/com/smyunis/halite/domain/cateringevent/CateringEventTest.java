package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.cateringevent.domainevents.CateringEventClosedEvent;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CateringEventTest {
    private CateringEvent cateringEvent;
    private CateringEventData data;

    @BeforeEach
    void setup() {
        data = new CateringEventData();
        cateringEvent = new CateringEvent(data);
    }

    @Test
    void moreAtLeastOnePersonWillBeAttendingTheEvent() {

        int randomNoOfAttendees = new Random().nextInt(1, 1000);
        var noOfExpectedAttendees = new NumberOfAttendees(randomNoOfAttendees);

        data.setExpectedNumberOfAttendees(noOfExpectedAttendees);
        NumberOfAttendees noOfAttendees = data.getExpectedNumberOfAttendees();

        assertEquals(noOfExpectedAttendees.numberOfAttendees(), noOfAttendees.numberOfAttendees());
        assertTrue(noOfAttendees.numberOfAttendees() >= 1);
        assertThrows(InvalidValueException.class, () -> {
            data.setExpectedNumberOfAttendees(new NumberOfAttendees(-1));
            data.setExpectedNumberOfAttendees(new NumberOfAttendees(0));
        });
    }


    @Test
    void eventEndTimeShouldBeAfterEventStartTime() {
        LocalDateTime now = LocalDateTime.now();
        data.setEventStartTime(now);

        LocalDateTime yesterday = now.minusDays(1);
        LocalDateTime tomorrow = now.plusDays(1);

        assertDoesNotThrow(() -> {
            data.setEventEndTime(tomorrow);
        });
        assertThrows(InvalidValueException.class, () -> {
            data.setEventEndTime(yesterday);
        });
    }

    @Test
    void canCloseAnOpenEvent() {
        cateringEvent.closeCateringEvent();

        assertThrows(InvalidOperationException.class,() -> {
           cateringEvent.closeCateringEvent();
        });
    }

    @Test
    void closingAnEventRaisesCateringEventClosedEvent() {
        cateringEvent.closeCateringEvent();

        var events = cateringEvent.getDomainEvents();

        assertEquals(CateringEventClosedEvent.class,events.get(0).getClass());
    }



}
