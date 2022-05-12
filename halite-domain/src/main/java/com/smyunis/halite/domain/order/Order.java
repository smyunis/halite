package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Order {
    private final OrderData data;

    public Order(OrderData data) {
        this.data = data;
    }

    public void addCateringMenuItem(CateringMenuItemId cateringMenuItemId, int quantity) {
        var orderedCateringMenuItems = data.getOrderedCateringMenuItems();
        orderedCateringMenuItems.add(new OrderedCateringMenuItem(cateringMenuItemId, quantity));
    }

    public Set<OrderedCateringMenuItem> getOrderedCateringMenuItems() {
        return data.getOrderedCateringMenuItems();
    }

    public void removeCateringMenuItem(CateringMenuItemId cateringMenuItemId) {
        var orderedCateringMenuItems = data.getOrderedCateringMenuItems();
        var orderedCateringMenuItemsWithRemoved = orderedCateringMenuItems.stream()
                .filter(o -> o.cateringMenuItemId() != cateringMenuItemId)
                .collect(Collectors.toSet());
        data.setOrderedCateringMenuItems(orderedCateringMenuItemsWithRemoved);
    }

}
