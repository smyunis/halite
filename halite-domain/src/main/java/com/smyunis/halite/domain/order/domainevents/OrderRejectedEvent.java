package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.order.Order;

public class OrderRejectedEvent extends DomainEvent {

    private final Order order;

    public OrderRejectedEvent(Order order) {

        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
