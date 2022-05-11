package com.smyunis.halite.application;

import com.smyunis.halite.domain.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DomainEventRegistrarTest {

    @Test
    void canAssignHandlerToARegisteredEvent() {
        var domainEventRegistrar = new DomainEventRegistrar();
        domainEventRegistrar.assignHandler(new DomainEvent() {
        }, (DomainEvent event) -> {
        });
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
