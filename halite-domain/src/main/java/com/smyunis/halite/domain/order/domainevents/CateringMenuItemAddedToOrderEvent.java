package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.order.Order;

public class CateringMenuItemAddedToOrderEvent extends DomainEvent {
    private final CateringMenuItemId itemId;
    private final Integer quantity;
    private final Order order;

    public CateringMenuItemAddedToOrderEvent(CateringMenuItemId itemId, Integer quantity, Order order) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.order = order;
    }

    public CateringMenuItemId getAddedItemId() {
        return itemId;
    }

    public Integer getAddedItemQuantity() {
        return quantity;
    }

    public Order getOrder() {
        return order;
    }
}
