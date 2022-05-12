package com.smyunis.halite.domain.cateringmenu;

import com.smyunis.halite.domain.caterer.CatererId;

public class CateringMenuData {
    private CateringMenuId id = new CateringMenuId();
    private CatererId ownerId;
    private String name;

    public CateringMenuId getId() {
        return id;
    }

    public CateringMenuData setId(CateringMenuId id) {
        this.id = id;
        return this;
    }

    public CatererId getOwnerId() {
        return ownerId;
    }

    public CateringMenuData setOwnerId(CatererId ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CateringMenuData setName(String name) {
        this.name = name;
        return this;
    }
}