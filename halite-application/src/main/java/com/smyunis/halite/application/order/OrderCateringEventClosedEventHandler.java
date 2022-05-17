package com.smyunis.halite.application.order;

import com.smyunis.halite.application.domaineventhandlers.DomainEventHandler;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringevent.domainevents.CateringEventClosedEvent;
import com.smyunis.halite.domain.order.OrderRepository;

public class OrderCateringEventClosedEventHandler implements DomainEventHandler<CateringEventClosedEvent> {
    private final OrderRepository orderRepository;

    public OrderCateringEventClosedEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handleEvent(CateringEventClosedEvent domainEvent) {
        CateringEventId closedCateringEventId = getClosedCateringEventId(domainEvent);
        orderRepository.cancelAllOrdersByCateringEventId(closedCateringEventId);
    }

    private CateringEventId getClosedCateringEventId(CateringEventClosedEvent domainEvent) {
        var closedCateringEvent = domainEvent.getClosedCateringEvent();
        return closedCateringEvent.getId();
    }
}
