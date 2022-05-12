package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.domain.DomainEventDispatcher;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.caterer.CatererRepository;

public class DomainEventsRegistrar {
    private final DomainEventDispatcher domainEventManager;
    private final CatererRepository catererRepository;

    public DomainEventsRegistrar(
            DomainEventDispatcher domainEventManager,
            CatererRepository catererRepository) {
        this.domainEventManager = domainEventManager;
        this.catererRepository = catererRepository;
    }

    public void assignHandlersForDomainEvents() {
        domainEventManager.assignHandler(BillSettledEvent.class, new CatererBillSettledEventHandler(catererRepository));
    }
}
