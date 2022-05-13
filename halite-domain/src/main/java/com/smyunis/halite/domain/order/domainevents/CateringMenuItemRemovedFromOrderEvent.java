package com.smyunis.halite.domain.order.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

public class CateringMenuItemRemovedFromOrderEvent extends DomainEvent {
    private final CateringMenuItemId itemId;

    public CateringMenuItemRemovedFromOrderEvent(CateringMenuItemId itemId) {
        this.itemId = itemId;
    }

    public CateringMenuItemId getRemovedItemId() {
        return itemId;
    }
}
