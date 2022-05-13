package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

public class CateringMenuItemAddedToOrderEvent extends DomainEvent {
    private final CateringMenuItemId itemId;

    public CateringMenuItemAddedToOrderEvent(CateringMenuItemId itemId) {
        this.itemId = itemId;
    }

    public CateringMenuItemId getAddedItemId() {
        return itemId;
    }
}
