package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.order.Order;

public class OrderCreatedEvent extends DomainEvent {
    private final Order order;

    public OrderCreatedEvent(Order order) {
        this.order = order;
    }

    public Order getCreatedOrder() {
        return order;
    }
}
