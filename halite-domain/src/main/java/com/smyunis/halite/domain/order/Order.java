package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.order.bill.BillStatus;
import com.smyunis.halite.domain.order.domainevents.*;
import com.smyunis.halite.domain.shared.MonetaryAmount;

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
        assertValidMenuItemQuantity(addedQuantity);
        addMenuItem(itemId, addedQuantity, orderedItems);
        domainEvents.add(new CateringMenuItemAddedToOrderEvent(itemId, addedQuantity, this));
    }

    private void assertValidMenuItemQuantity(int addedQuantity) {
        if (!isValidMenuItemQuantity(addedQuantity))
            throw new InvalidValueException("Invalid catering menu item quantity");
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
        assertOrderIsAcceptedOrPendingAcceptance();
        data.setStatus(OrderStatus.CANCELLED);
    }

    public void settleBill() {
        data.getBill().settle();
        domainEvents.add(new BillSettledEvent(this));
    }

    public void requestBillCancellation() {
        data.getBill().requestCancellation();
    }

    public void approveBillCancellation() {
        data.getBill().approveCancellation();
    }

    public BillStatus getBillStatus() {
        return data.getBill().getBillStatus();
    }

    public MonetaryAmount getBillOutstandingAmount() {
        return data.getBill().getOutstandingAmount();
    }

    public void incrementBillOutstandingAmount(MonetaryAmount addend) {
        data.getBill().incrementOutstandingAmount(addend);
    }

    public void decrementBillOutstandingAmount(MonetaryAmount subtrahend) {
        data.getBill().decrementOutstandingAmount(subtrahend);
    }

    private boolean orderIsNotAccepted() {
        return data.getStatus() != OrderStatus.ACCEPTED;
    }

    public CatererId getCatererId() {
        return data.getCatererId();
    }

    public CateringEventHostId getCateringEventHostId() {
        return data.getCateringEventHostId();
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }

    private void assertOrderIsAcceptedOrPendingAcceptance() {
        if (!(data.getStatus() == OrderStatus.PENDING_ACCEPTANCE || data.getStatus() == OrderStatus.ACCEPTED))
            throw new InvalidOperationException("Order is not accepted nor pending acceptance");
    }

    private void assertOrderIsAccepted() {
        if (orderIsNotAccepted())
            throw new InvalidOperationException("Unaccepted Orders Can Not be Fulfilled");
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

    public OrderId getId() {
        return data.getId();
    }
}
