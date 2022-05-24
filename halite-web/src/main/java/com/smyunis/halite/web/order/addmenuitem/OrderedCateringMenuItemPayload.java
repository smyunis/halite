package com.smyunis.halite.web.order.addmenuitem;

public class OrderedCateringMenuItemPayload {
    private String orderedMenuItemId;
    private int orderedQuantity;

    public String getOrderedMenuItemId() {
        return orderedMenuItemId;
    }

    public OrderedCateringMenuItemPayload setOrderedMenuItemId(String orderedMenuItemId) {
        this.orderedMenuItemId = orderedMenuItemId;
        return this;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public OrderedCateringMenuItemPayload setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
        return this;
    }
}

