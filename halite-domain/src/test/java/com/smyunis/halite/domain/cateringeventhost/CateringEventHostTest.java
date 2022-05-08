package com.smyunis.halite.domain.cateringeventhost;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringevent.CateringEvent;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.domainevents.CateringEventUpdatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CateringEventHostTest {
    private CateringEventHost cateringEventHost;

    @BeforeEach
    void setup() {
        cateringEventHost = new CateringEventHost();
    }

    @Test
    void newClientHasARandomlyGeneratedNewId() {
        assertNotNull(cateringEventHost.getId().toString());
        assertNotEquals("", cateringEventHost.getId().toString());
    }

    @Test
    void canSetIdOnNewClient() {
        String newId = "42b32f8d-a52d-4dfe-800a-911936729730";

        cateringEventHost.setId(new CateringEventHostId(newId));

        assertEquals(newId, cateringEventHost.getId().toString());
    }

    @Test
    void canSetAndGetClientPhoneNumber() {
        String phoneNumber = "0929186232";

        cateringEventHost.setPhoneNumber(new PhoneNumber(phoneNumber));

        assertEquals(phoneNumber, cateringEventHost.getPhoneNumber().phoneNumber());
    }

    @Test
    void canHostANewEvent() {
        CateringEventId cateringEventId = new CateringEventId();
        cateringEventHost.hostNewCateringEvent(cateringEventId);

        assertTrue(cateringEventHost.getHostedCateringEvents().contains(cateringEventId));
    }

    @Test
    void canRemoveHostedCateringEvent() {
        CateringEventId cateringEventId = new CateringEventId();
        cateringEventHost.hostNewCateringEvent(cateringEventId);

        cateringEventHost.removeCateringEvent(cateringEventId);

        assertFalse(cateringEventHost.getHostedCateringEvents().contains(cateringEventId));
    }

    @Test
    void canRaiseCateringEventUpdatedEventWhenUpdate() {
        CateringEvent cateringEvent = new CateringEvent();

        cateringEventHost.updateCateringEvent(cateringEvent);

        List<DomainEvent> domainEvents = cateringEventHost.getDomainEvents();
        assertTrue(domainEvents.get(0) instanceof CateringEventUpdatedEvent);
    }


}
