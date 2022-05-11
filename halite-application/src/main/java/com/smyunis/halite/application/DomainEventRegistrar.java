package com.smyunis.halite.application;

import com.smyunis.halite.domain.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class DomainEventRegistrar {

    private final List<DomainEvent> registeredDomainEvents = new ArrayList<>();
    private final List<DomainEventHandlerCatalogue> domainEventHandlerCatalogue = new ArrayList<>();

    public void assignHandler(Class<? extends DomainEvent> domainEventClass, DomainEventHandler handler) {
        domainEventHandlerCatalogue.add(new DomainEventHandlerCatalogue(domainEventClass, handler));
    }

    public void registerDomainEvents(List<DomainEvent> domainEvents) {
        registeredDomainEvents.addAll(domainEvents);
    }

    public void publish() {
        for (var event : registeredDomainEvents) {
            var handlerPairs = domainEventHandlerCatalogue.stream()
                    .filter(e -> e.domainEventClass.equals(event.getClass())).toList();
            handlerPairs.forEach(e -> e.domainEventHandler().handleEvent(event));
        }
    }

    private record DomainEventHandlerCatalogue(Class<? extends DomainEvent> domainEventClass, DomainEventHandler domainEventHandler) {
    }
}


