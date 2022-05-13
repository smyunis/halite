package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderData {
    private OrderId id = new OrderId();
    private OrderStatus status = OrderStatus.PENDING_ACCEPTANCE;
    private CateringEventId cateringEventId;
    private CateringEventHostId cateringEventHostId;
    private CatererId catererId;
    private Map<CateringMenuItemId, Integer> orderedCateringMenuItems = new HashMap<>();

    public OrderId getId() {
        return id;
    }

    public OrderData setId(OrderId id) {
        this.id = id;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderData setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public CateringEventId getCateringEventId() {
        return cateringEventId;
    }

    public OrderData setCateringEventId(CateringEventId cateringEventId) {
        this.cateringEventId = cateringEventId;
        return this;
    }

    public CateringEventHostId getCateringEventHostId() {
        return cateringEventHostId;
    }

    public OrderData setCateringEventHostId(CateringEventHostId cateringEventHostId) {
        this.cateringEventHostId = cateringEventHostId;
        return this;
    }

    public CatererId getCatererId() {
        return catererId;
    }

    public OrderData setCatererId(CatererId catererId) {
        this.catererId = catererId;
        return this;
    }

    public Map<CateringMenuItemId, Integer> getOrderedCateringMenuItems() {
        return orderedCateringMenuItems;
    }

    public OrderData setOrderedCateringMenuItems(Map<CateringMenuItemId, Integer> orderedCateringMenuItems) {
        for (var quantity : orderedCateringMenuItems.values()) {
            if (quantity < 1)
                throw new InvalidValueException(this.getClass().getName());
        }
        this.orderedCateringMenuItems = orderedCateringMenuItems;
        return this;
    }
}
