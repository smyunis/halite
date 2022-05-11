package com.smyunis.halite.application;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

public class DomainEventRegistrarTest {

    @Test
    void canAssignHandlerToARegisteredEvent() {
        var domainEventRegistrar = new DomainEventRegistrar();
        domainEventRegistrar.assignHandler(BillSettledEvent.class, (DomainEvent event) -> {});
    }

    @Test
    void canPublishEventsWhoHadHandlersAssignedToThem() {
        var domainEventRegistrar = new DomainEventRegistrar();
        domainEventRegistrar.publish();
    }

    @Test
    void canRegisterDomainEvents() {
        var domainEventRegistrar = new DomainEventRegistrar();
        domainEventRegistrar.registerDomainEvents(new ArrayList<DomainEvent>());
    }
}
