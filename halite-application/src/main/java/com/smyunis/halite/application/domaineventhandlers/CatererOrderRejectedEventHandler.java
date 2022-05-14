package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.order.domainevents.OrderRejectedEvent;

public class CatererOrderRejectedEventHandler implements DomainEventHandler<OrderRejectedEvent> {
    private final CatererRepository catererRepository;

    public CatererOrderRejectedEventHandler(CatererRepository catererRepository) {
        this.catererRepository = catererRepository;
    }

    @Override
    public void handleEvent(OrderRejectedEvent event) {
        Caterer caterer = getCaterer(event);
        caterer.handleOrderRejectedEvent();
        catererRepository.save(caterer);
    }

    private Caterer getCaterer(OrderRejectedEvent event) {
        var order = event.getOrder();
        return catererRepository.get(order.getCatererId());
    }
}
