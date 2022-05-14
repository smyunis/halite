package com.smyunis.halite.application.order;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderId;
import com.smyunis.halite.domain.order.OrderRepository;

public class OrderService {
    private final DomainEventDispatcher eventDispatcher;
    private final OrderRepository orderRepository;

    public OrderService(DomainEventDispatcher eventDispatcher, OrderRepository orderRepository) {
        this.eventDispatcher = eventDispatcher;
        this.orderRepository = orderRepository;
    }

    public void acceptOrder(OrderId orderId) {
        Order order = orderRepository.get(orderId);
        order.accept();
        orderRepository.save(order);
    }

    public void rejectOrder(OrderId orderId) {
        Order order = orderRepository.get(orderId);
        order.reject();
        dispatchEvents(order);
        orderRepository.save(order);
    }

    private void dispatchEvents(Order order) {
        eventDispatcher.registerDomainEvents(order.getDomainEvents());
        eventDispatcher.publish();
    }
}
