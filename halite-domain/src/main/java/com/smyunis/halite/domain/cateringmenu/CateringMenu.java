package com.smyunis.halite.domain.cateringmenu;

import com.smyunis.halite.domain.caterer.CatererId;

public class CateringMenu {
    private CateringMenuId id = new CateringMenuId();
    private CatererId catererId;
    private String name;

    public CateringMenuId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public CatererId getCatererId() {
        return catererId;
    }

    void setCatererId(CatererId catererId) {
        this.catererId = catererId;
    }
}
