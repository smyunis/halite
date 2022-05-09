package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Order {
    private OrderId id;
    private OrderStatus status = OrderStatus.Pending;
    private CateringEventId cateringEventId;
    private CateringEventHostId cateringEventHostId;
    private CatererId catererId;
    private Set<OrderedCateringMenuItem> orderedCateringMenuItems = new HashSet<>();

    public void addCateringMenuItem(CateringMenuItemId cateringMenuItemId, int quantity) {
        orderedCateringMenuItems.add(new OrderedCateringMenuItem(cateringMenuItemId,quantity));
    }

    public Set<OrderedCateringMenuItem> getOrderedCateringMenuItems() {
        return orderedCateringMenuItems;
    }

    public void removeCateringMenuItem(CateringMenuItemId cateringMenuItemId) {
        orderedCateringMenuItems = orderedCateringMenuItems.stream()
                .filter(o -> o.cateringMenuItemId() != cateringMenuItemId)
                .collect(Collectors.toSet());
    }


}
