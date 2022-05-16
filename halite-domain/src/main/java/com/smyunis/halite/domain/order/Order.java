package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemRemovedFromOrderEvent;
import com.smyunis.halite.domain.order.domainevents.OrderFulfilledEvent;
import com.smyunis.halite.domain.order.domainevents.OrderRejectedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Order {
    private final OrderData data;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Order(OrderData data) {
        this.data = data;
    }

    public void addCateringMenuItem(CateringMenuItemId itemId, int addedQuantity) {
        var orderedItems = data.getOrderedCateringMenuItems();

        if (!isValidMenuItemQuantity(addedQuantity))
            throw new InvalidValueException("Invalid catering menu item quantity");

        addMenuItem(itemId, addedQuantity, orderedItems);

        domainEvents.add(new CateringMenuItemAddedToOrderEvent(itemId, addedQuantity, this));
    }

    private void addMenuItem(CateringMenuItemId itemId, int quantity, Map<CateringMenuItemId, Integer> orderedItems) {
        orderedItems.computeIfPresent(itemId, (cateringMenuItemId, prevQuantity) -> prevQuantity + quantity);
        orderedItems.putIfAbsent(itemId, quantity);
    }


    public Map<CateringMenuItemId, Integer> getOrderedCateringMenuItems() {
        return Collections.unmodifiableMap(data.getOrderedCateringMenuItems());
    }

    public void removeCateringMenuItem(CateringMenuItemId itemId, int quantityToBeRemoved) {
        var orderedCateringMenuItems = data.getOrderedCateringMenuItems();
        removeMenuItems(itemId, quantityToBeRemoved, orderedCateringMenuItems);
        domainEvents.add(new CateringMenuItemRemovedFromOrderEvent(itemId, quantityToBeRemoved, this));
    }

    private void removeMenuItems(CateringMenuItemId itemId, int quantityToBeRemoved, Map<CateringMenuItemId, Integer> orderedCateringMenuItems) {
        if (orderedCateringMenuItems.get(itemId) > quantityToBeRemoved)
            orderedCateringMenuItems.computeIfPresent(itemId, (id, prevQuantity) -> prevQuantity - quantityToBeRemoved);
        else
            orderedCateringMenuItems.remove(itemId);
    }


    public void accept() {
        assertOrderIsPendingAcceptance();
        data.setStatus(OrderStatus.ACCEPTED);
    }

    private void assertOrderIsPendingAcceptance() {
        if (orderIsNotPendingAcceptance())
            throw new InvalidOperationException("Order is not pending acceptance");
    }

    public void reject() {
        assertOrderIsNotRejected();
        data.setStatus(OrderStatus.REJECTED);
        domainEvents.add(new OrderRejectedEvent(this));
    }

    private void assertOrderIsNotRejected() {
        if (isOrderRejected())
            throw new InvalidOperationException("Order is already rejected");
    }

    public void fulfill() {
        assertOrderIsAccepted();
        data.setStatus(OrderStatus.FULFILLED);
        domainEvents.add(new OrderFulfilledEvent(this));
    }

    public void cancel() {
        assertOrderIsAccepted();
        data.setStatus(OrderStatus.CANCELLED);
    }

    private void assertOrderIsAccepted() {
        if (isOrderNotAccepted())
            throw new InvalidOperationException("Unaccepted Orders Can Not be Fulfilled");
    }

    private boolean isOrderNotAccepted() {
        return data.getStatus() != OrderStatus.ACCEPTED;
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }

    private boolean isValidMenuItemQuantity(int quantity) {
        return quantity >= 1;
    }

    private boolean isOrderRejected() {
        return data.getStatus() == OrderStatus.REJECTED;
    }

    private boolean orderIsNotPendingAcceptance() {
        return data.getStatus() != OrderStatus.PENDING_ACCEPTANCE;
    }

    public BillId getBillId() {
        return data.getBillId();
    }

    public CatererId getCatererId() {
        return data.getCatererId();
    }

    public CateringEventHostId getCateringEventHostId() {
        return data.getCateringEventHostId();
    }


}
