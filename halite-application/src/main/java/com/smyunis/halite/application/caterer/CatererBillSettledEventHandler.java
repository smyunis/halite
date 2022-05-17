package com.smyunis.halite.application.caterer;

import com.smyunis.halite.application.domaineventhandlers.DomainEventHandler;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.order.domainevents.BillSettledEvent;

public class CatererBillSettledEventHandler implements DomainEventHandler<BillSettledEvent> {
    private final CatererRepository catererRepository;

    public CatererBillSettledEventHandler(CatererRepository catererRepository) {
        this.catererRepository = catererRepository;
    }

    @Override
    public void handleEvent(BillSettledEvent domainEvent) {
        var order = domainEvent.getOrder();
        var payeeCatererId = order.getCatererId();

        Caterer payeeCaterer = catererRepository.get(payeeCatererId);
        payeeCaterer.handleBillSettledEvent(order);
        catererRepository.save(payeeCaterer);
    }
}
