package com.smyunis.halite.application.order;

import com.smyunis.halite.application.domaineventhandlers.DomainEventHandler;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.domainevents.CateringMenuItemRemovedEvent;
import com.smyunis.halite.domain.order.OrderRepository;

public class OrderCateringMenuItemRemovedEventHandler implements DomainEventHandler<CateringMenuItemRemovedEvent> {
    private final OrderRepository orderRepository;

    public OrderCateringMenuItemRemovedEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handleEvent(CateringMenuItemRemovedEvent domainEvent) {
        CateringMenuItemId removedItemId = domainEvent.getRemovedItemId();
        orderRepository.removeMenuItemFromAllOrdersPendingAcceptanceByMenuItemId(removedItemId);
    }
}
