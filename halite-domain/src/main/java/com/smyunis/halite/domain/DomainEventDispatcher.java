package com.smyunis.halite.domain;

import java.util.ArrayList;
import java.util.List;

public class DomainEventDispatcher {

    private final List<DomainEvent> registeredDomainEvents = new ArrayList<>();
    private final static List<DomainEventHandlerCatalogue> domainEventHandlerCatalogue = new ArrayList<>();

    public void assignHandler(Class<? extends DomainEvent> domainEventClass,
                              DomainEventHandler<? extends DomainEvent> handler) {
        domainEventHandlerCatalogue.add(new DomainEventHandlerCatalogue(domainEventClass, handler));
    }

    public void registerDomainEvents(List<DomainEvent> domainEvents) {
        registeredDomainEvents.addAll(domainEvents);
    }

    public void publish() {
        for (var event : registeredDomainEvents) {
            var handlerPairs = domainEventHandlerCatalogue.stream()
                    .filter(e -> e.domainEventClass.equals(event.getClass())).toList();
            handlerPairs.forEach(e -> e.domainEventHandler().handleEvent(
                    e.domainEventClass().cast(event)));
        }
        registeredDomainEvents.clear();
    }

    private record DomainEventHandlerCatalogue(
            Class<? extends DomainEvent> domainEventClass,
            DomainEventHandler domainEventHandler) {
    }
}


