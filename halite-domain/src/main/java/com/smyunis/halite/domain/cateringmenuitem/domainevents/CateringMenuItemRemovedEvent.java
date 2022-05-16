package com.smyunis.halite.domain.cateringmenuitem.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

public class CateringMenuItemRemovedEvent extends DomainEvent {
    private final CateringMenuItemId removedItemId;

    public CateringMenuItemRemovedEvent(CateringMenuItemId removedItemId) {
        this.removedItemId = removedItemId;
    }

    public CateringMenuItemId getRemovedItemId() {
        return removedItemId;
    }
}
