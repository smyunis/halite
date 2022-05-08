package com.smyunis.halite.domain.cateringmenu;

import com.smyunis.halite.domain.caterer.domainevents.CateringMenuUpdatedEvent;

import java.util.List;

public class CateringMenu {
    private CateringMenuId id = new CateringMenuId();
    private String name;
    private List<CateringMenuItem> cateringMenuItems;

    public CateringMenuId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateCateringMenu(CateringMenu newCateringMenu) {
        this.name = newCateringMenu.name;
        this.id = newCateringMenu.id;
        this.cateringMenuItems = newCateringMenu.cateringMenuItems;
    }

    public class DomainEventHandler {
        public void handleCateringMenuUpdated(CateringMenuUpdatedEvent cateringMenuUpdatedEvent) {
            CateringMenu updatedCateringMenu = cateringMenuUpdatedEvent.getUpdatedCateringMenu();
            name = updatedCateringMenu.name;
            cateringMenuItems = updatedCateringMenu.cateringMenuItems;
        }
    }
}
