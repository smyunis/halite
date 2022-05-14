package com.smyunis.halite.domain.cateringmenuitem;


import com.smyunis.halite.domain.shared.MonetaryAmount;

public class CateringMenuItem {
    private final CateringMenuItemData data;
    public CateringMenuItem(CateringMenuItemData data) {
        this.data = data;
    }

    public MonetaryAmount getPrice() {
        return data.getPrice();
    }
}
