package com.smyunis.halite.domain.caterer.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenu.CateringMenu;

public class CateringMenuUpdatedEvent extends DomainEvent {
    private final CateringMenu updatedCateringMenu;

    public CateringMenuUpdatedEvent(CateringMenu updatedCateringMenu) {
        this.updatedCateringMenu = updatedCateringMenu;
    }

    public CateringMenu getUpdatedCateringMenu() {
        return updatedCateringMenu;
    }

}
