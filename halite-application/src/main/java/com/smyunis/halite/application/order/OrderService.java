package com.smyunis.halite.application.order;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.OrderId;
import com.smyunis.halite.domain.order.OrderRepository;

public class OrderService {
    private final DomainEventDispatcher eventDispatcher;
    private final OrderRepository orderRepository;
    private final CateringMenuItemRepository cateringMenuItemRepository;

    public OrderService(DomainEventDispatcher eventDispatcher,
                        OrderRepository orderRepository,
                        CateringMenuItemRepository cateringMenuItemRepository) {
        this.eventDispatcher = eventDispatcher;
        this.orderRepository = orderRepository;
        this.cateringMenuItemRepository = cateringMenuItemRepository;
    }

    public void acceptOrder(OrderId orderId) {
        Order order = orderRepository.get(orderId);
        order.accept();
        orderRepository.save(order);
    }

    public void rejectOrder(OrderId orderId) {
        Order order = orderRepository.get(orderId);
        order.reject();
        saveAndDispatchDomainEvents(order);
    }

    public void createOrder(OrderData orderData) {
        Order order = new Order(orderData);
        order.asNewOrder();
        saveAndDispatchDomainEvents(order);
    }

    public void cancelOrder(OrderId orderId) {
        Order orderToBeCanceled = orderRepository.get(orderId);
        orderToBeCanceled.cancel();
        orderRepository.save(orderToBeCanceled);
    }

    public void fulfill(OrderId orderId) {
        Order orderToBeFulfilled = orderRepository.get(orderId);
        orderToBeFulfilled.fulfill();
        saveAndDispatchDomainEvents(orderToBeFulfilled);
    }

    public void addCateringMenuItem(OrderId orderId, CateringMenuItemId itemId, int quantity) {
        Order order = orderRepository.get(orderId);
        assertOrderedCatererOwnsMenuItem(itemId, order);
        order.addCateringMenuItem(itemId, quantity);
        saveAndDispatchDomainEvents(order);
    }

    private void assertOrderedCatererOwnsMenuItem(CateringMenuItemId itemId, Order order) {
        CatererId orderedCatererId = order.getCatererId();
        CateringMenuItem cateringMenuItem = cateringMenuItemRepository.get(itemId);
        var catererForMenuItem = cateringMenuItem.getCatererId();

        if(catererForMenuItem != orderedCatererId)
            throw new InvalidOperationException("This Menu Item does not belong to the caterer this order is made for");
    }

    public void removeCateringMenuItem(OrderId orderId, CateringMenuItemId itemId, int quantity) {
        Order order = orderRepository.get(orderId);
        order.removeCateringMenuItem(itemId, quantity);
        saveAndDispatchDomainEvents(order);
    }

    private void saveAndDispatchDomainEvents(Order order) {
        orderRepository.save(order);
        dispatchEvents(order);
    }

    private void dispatchEvents(Order order) {
        eventDispatcher.registerDomainEvents(order.getDomainEvents());
        eventDispatcher.publish();
    }
}
