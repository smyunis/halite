package com.smyunis.halite.application.order;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.order.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderId orderId;
    private Order order;
    private OrderData orderData;
    private OrderRepository orderRepository;
    private DomainEventDispatcher eventDispatcher;

    @BeforeEach
    void setup() {
        orderId = new OrderId();
        orderData = new OrderData().setId(orderId);
        order = new Order(orderData);
        orderRepository = mock(OrderRepository.class);
        when(orderRepository.get(orderId)).thenReturn(order);
        eventDispatcher = mock(DomainEventDispatcher.class);
        orderService = new OrderService(eventDispatcher, orderRepository);
    }

    @Test
    void catererCanAcceptAnOrderOnce() {
        orderService.acceptOrder(orderId);

        verify(orderRepository).get(orderId);
        verify(orderRepository).save(order);
        assertThrows(InvalidOperationException.class, () -> {
            orderService.acceptOrder(orderId);
        });
    }


    @Test
    void catererCanRejectOrderOnce() {
        orderService.rejectOrder(orderId);

        verify(orderRepository).get(orderId);
        verify(orderRepository).save(order);

        assertThrows(InvalidOperationException.class, () -> {
            orderService.rejectOrder(orderId);
        });
    }

    @Test
    void rejectingAnOrderEmitsAnOrderRejectedEvent() {
        orderService.rejectOrder(orderId);

        verify(eventDispatcher).registerDomainEvents(anyList());
        verify(eventDispatcher).publish();
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void createANewOrder() {
        orderService.createOrder(orderData);

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void cancelAnOrder() {
        orderService.cancelOrder(orderId);

        verify(orderRepository).get(orderId);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void fulfillAnOrder() {
        orderData.setStatus(OrderStatus.ACCEPTED);

        orderService.fulfill(orderId);

        verify(orderRepository).get(orderId);
        verify(orderRepository).save(any(Order.class));
        verify(eventDispatcher).registerDomainEvents(anyList());
        verify(eventDispatcher).publish();
    }

}
