package com.smyunis.halite.domain.cateringmenuitem;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringmenuitem.domainevents.CateringMenuItemRemovedEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CateringMenuItem {
    private final CateringMenuItemData data;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public CateringMenuItem(CateringMenuItemData data) {
        this.data = data;
    }

    public MonetaryAmount getPrice() {
        return data.getPrice();
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public CateringMenuItem asRemovedMenuItem() {
        domainEvents.add(new CateringMenuItemRemovedEvent(data.getId()));
        return this;
    }

    public CatererId getCatererId() {
        return data.getCatererId();
    }
}
