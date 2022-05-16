package com.smyunis.halite.application.caterer;

import com.smyunis.halite.application.caterer.CatererOrderRejectedEventHandler;
import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.application.order.OrderService;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.OrderId;
import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.domain.order.domainevents.OrderRejectedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CatererOrderRejectedEventHandlerTest {
    private OrderService orderService;
    private OrderId orderId;
    private CatererRepository catererRepository;
    private CatererId catererId;


    @BeforeEach
    void setup() {
        orderId = new OrderId();
        catererId = new CatererId();
        OrderData orderData = new OrderData()
                .setId(orderId)
                .setCatererId(catererId);
        Order order = new Order(orderData);

        DomainEventDispatcher eventDispatcher = new DomainEventDispatcher();
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.get(orderId)).thenReturn(order);
        orderService = new OrderService(eventDispatcher, orderRepository);

        catererRepository = mock(CatererRepository.class);
        when(catererRepository.get(catererId)).thenReturn(new Caterer(new CatererData().setId(catererId)));
        CatererOrderRejectedEventHandler eventhandler = new CatererOrderRejectedEventHandler(catererRepository);
        eventDispatcher.assignHandler(OrderRejectedEvent.class, eventhandler);
    }


    @Test
    void canHandleOrderRejectedEvent() {
        orderService.rejectOrder(orderId);

        verify(catererRepository).get(catererId);
        verify(catererRepository).save(any());
        assertEquals(-1,catererRepository.get(catererId).getRecommendationMetric());
    }
}
