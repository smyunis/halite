package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.order.bill.Bill;

import java.util.Map;

public class OrderDataReadOnlyProxy {
    private final OrderData data;

    public OrderDataReadOnlyProxy(OrderData data) {
        this.data = data;
    }

    public OrderId getId() {
        return data.getId();
    }

    public OrderStatus getStatus() {
        return data.getStatus();
    }

    public CateringEventId getCateringEventId() {
        return data.getCateringEventId();
    }

    public CateringEventHostId getCateringEventHostId() {
        return data.getCateringEventHostId();
    }

    public CatererId getCatererId() {
        return data.getCatererId();
    }

    public Bill getBill() {
        return data.getBill();
    }

    public Map<CateringMenuItemId, Integer> getOrderedCateringMenuItems() {
        return data.getOrderedCateringMenuItems();
    }
}
