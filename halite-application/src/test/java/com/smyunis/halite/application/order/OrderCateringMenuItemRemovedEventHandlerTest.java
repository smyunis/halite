package com.smyunis.halite.application.order;

import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.domainevents.CateringMenuItemRemovedEvent;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.OrderId;
import com.smyunis.halite.domain.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class OrderCateringMenuItemRemovedEventHandlerTest {
    private OrderCateringMenuItemRemovedEventHandler eventHandler;
    private OrderRepository orderRepository;
    private OrderId orderId;
    private CateringMenuItemId cateringMenuItemId;

    @BeforeEach
    void setup() {
        orderId = new OrderId();
        cateringMenuItemId = new CateringMenuItemId();
        orderRepository = mock(OrderRepository.class);

        eventHandler = new OrderCateringMenuItemRemovedEventHandler(orderRepository);
    }

    @Test
    void canOrderCateringMenuItemRemovedEvent() {
        var orderedItems = new HashMap<CateringMenuItemId, Integer>();
        orderedItems.put(cateringMenuItemId, 5);
        Order order = new Order(new OrderData().setId(orderId).setOrderedCateringMenuItems(orderedItems));
        when(orderRepository.get(orderId)).thenReturn(order);
        doAnswer(i -> {
            order.removeCateringMenuItem(cateringMenuItemId, 5);
            return null;
        })
                .when(orderRepository)
                .removeMenuItemFromAllOrdersPendingAcceptanceByMenuItemId(cateringMenuItemId);

        var event = new CateringMenuItemRemovedEvent(cateringMenuItemId);
        eventHandler.handleEvent(event);

        assertFalse(orderRepository.get(orderId).getOrderedCateringMenuItems().containsKey(cateringMenuItemId));
    }
}
