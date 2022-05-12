package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Order {
    private final OrderData data;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Order(OrderData data) {
        this.data = data;
    }

    public void addCateringMenuItem(CateringMenuItemId itemId, int quantity) {
        var orderedCateringMenuItems = data.getOrderedCateringMenuItems();
        orderedCateringMenuItems.add(new OrderedCateringMenuItem(itemId, quantity));
    }

    public Set<OrderedCateringMenuItem> getOrderedCateringMenuItems() {
        return data.getOrderedCateringMenuItems();
    }

    public void removeCateringMenuItem(CateringMenuItemId itemId) {
        var orderedCateringMenuItems = data.getOrderedCateringMenuItems();
        Set<OrderedCateringMenuItem> orderedCateringMenuItemsWithRemoved =
                filterByCateringMenuItemId(itemId, orderedCateringMenuItems);
        data.setOrderedCateringMenuItems(orderedCateringMenuItemsWithRemoved);
    }

    private Set<OrderedCateringMenuItem> filterByCateringMenuItemId(CateringMenuItemId itemId, Set<OrderedCateringMenuItem> menuItems) {
        return menuItems.stream()
                .filter(o -> o.cateringMenuItemId() != itemId)
                .collect(Collectors.toSet());
    }

    public void accept() {
        if (orderCanNotBeAccepted())
            throw new InvalidOperationException("Order is not pending acceptance");
        data.setStatus(OrderStatus.ACCEPTED);
    }

    private boolean orderCanNotBeAccepted() {
        return data.getStatus() != OrderStatus.PENDING_ACCEPTANCE;
    }

    public void reject() {
        if (isOrderRejected())
            throw new InvalidOperationException("Order is already rejected");
        data.setStatus(OrderStatus.REJECTED);
    }

    private boolean isOrderRejected() {
        return data.getStatus() == OrderStatus.REJECTED;
    }

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }
}
