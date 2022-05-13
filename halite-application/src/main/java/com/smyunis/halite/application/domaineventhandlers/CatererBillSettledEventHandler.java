package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererRepository;

public class CatererBillSettledEventHandler implements DomainEventHandler<BillSettledEvent> {
    private final CatererRepository catererRepository;

    public CatererBillSettledEventHandler(CatererRepository catererRepository) {
        this.catererRepository = catererRepository;
    }

    @Override
    public void handleEvent(BillSettledEvent domainEvent) {
        var settledBill = domainEvent.getBill();
        var payeeCatererId = settledBill.getPayeeId();

        Caterer payeeCaterer = catererRepository.get(payeeCatererId);

        payeeCaterer.new BillSettledEventHandler()
                .handleEvent(domainEvent);

        catererRepository.save(payeeCaterer);
    }
}
