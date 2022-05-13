package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

public class CateringMenuItemRemovedFromOrderEvent extends DomainEvent {
    private final CateringMenuItemId itemId;
    private final Integer quantity;

    public CateringMenuItemRemovedFromOrderEvent(CateringMenuItemId itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public CateringMenuItemId getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
