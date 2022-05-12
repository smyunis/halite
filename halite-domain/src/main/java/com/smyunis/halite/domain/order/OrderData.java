package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;

import java.util.HashSet;
import java.util.Set;

public class OrderData {
    private OrderId id = new OrderId();
    private OrderStatus status = OrderStatus.PENDING_ACCEPTANCE;
    private CateringEventId cateringEventId;
    private CateringEventHostId cateringEventHostId;
    private CatererId catererId;
    private Set<OrderedCateringMenuItem> orderedCateringMenuItems = new HashSet<>();

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

    public Set<OrderedCateringMenuItem> getOrderedCateringMenuItems() {
        return orderedCateringMenuItems;
    }

    public OrderData setOrderedCateringMenuItems(Set<OrderedCateringMenuItem> orderedCateringMenuItems) {
        this.orderedCateringMenuItems = orderedCateringMenuItems;
        return this;
    }
}
