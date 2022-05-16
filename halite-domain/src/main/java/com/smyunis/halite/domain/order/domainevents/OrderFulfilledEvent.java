package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.order.Order;

public class OrderFulfilledEvent extends DomainEvent {
    private final Order fulfilledOrder;

    public OrderFulfilledEvent(Order fulfilledOrder) {
        this.fulfilledOrder = fulfilledOrder;
    }

    public Order getFulfilledOrder() {
        return fulfilledOrder;
    }
}
