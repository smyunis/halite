package com.smyunis.halite.domain.cateringmenu;

import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

import java.util.Set;

public class CateringMenu {
    private CateringMenuId id = new CateringMenuId();
    private String name;
    private Set<CateringMenuItemId> cateringMenuItemIds;

    public CateringMenuId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

}
