package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.domain.DomainEventsDispatcher;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.caterer.CatererRepository;

public class DomainEventsRegistrar {
    private final DomainEventsDispatcher domainEventManager;
    private final CatererRepository catererRepository;

    public DomainEventsRegistrar(
            DomainEventsDispatcher domainEventManager,
            CatererRepository catererRepository) {
        this.domainEventManager = domainEventManager;
        this.catererRepository = catererRepository;
    }

    public void assignHandlersForDomainEvents() {
        domainEventManager.assignHandler(BillSettledEvent.class, new CatererBillSettledEventHandler(catererRepository));
    }
}
