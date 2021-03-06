package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.order.Order;

public class BillSettledEvent extends DomainEvent {
    private final Order order;

    public BillSettledEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
