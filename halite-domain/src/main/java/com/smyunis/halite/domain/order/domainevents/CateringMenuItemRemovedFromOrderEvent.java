package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.order.Order;

public class CateringMenuItemRemovedFromOrderEvent extends DomainEvent {
    private final CateringMenuItemId itemId;
    private final Integer quantity;
    private final Order order;

    public CateringMenuItemRemovedFromOrderEvent(CateringMenuItemId itemId, Integer quantity, Order order) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.order = order;
    }

    public CateringMenuItemId getItemId() {
        return itemId;
    }

    public Integer getRemovedItemQuantity() {
        return quantity;
    }

    public Order getOrder() {
        return order;
    }
}
