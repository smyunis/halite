package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

public class CateringMenuItemAddedToOrderEvent extends DomainEvent {
    private final CateringMenuItemId itemId;
    private final Integer quantity;

    public CateringMenuItemAddedToOrderEvent(CateringMenuItemId itemId,Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public CateringMenuItemId getAddedItemId() {
        return itemId;
    }

    public Integer getAddedItemQuantity() {
        return quantity;
    }
}
