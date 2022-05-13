package com.smyunis.halite.domain.cateringmenuitem;


public class CateringMenuItem {
    private final CateringMenuItemData data;
    public CateringMenuItem(CateringMenuItemData data) {
        this.data = data;
    }

    public double getPrice() {
        return data.getPrice();
    }
}
