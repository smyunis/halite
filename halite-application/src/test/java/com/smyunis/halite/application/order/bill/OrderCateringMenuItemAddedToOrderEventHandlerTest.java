package com.smyunis.halite.application.order.bill;

import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.OrderId;
import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.domain.order.bill.Bill;
import com.smyunis.halite.domain.order.bill.BillData;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderCateringMenuItemAddedToOrderEventHandlerTest {
    private OrderCateringMenuItemAddedToOrderEventHandler eventHandler;
    private CateringMenuItemId cateringMenuItemId;
    private CateringMenuItemRepository cateringMenuItemRepository;
    private OrderRepository orderRepository;
    private OrderId orderId;
    private Order order;

    @BeforeEach
    void setup() {
        orderId = new OrderId();
        cateringMenuItemId = new CateringMenuItemId();
        orderRepository = mock(OrderRepository.class);
        cateringMenuItemRepository = mock(CateringMenuItemRepository.class);
        order = new Order(new OrderData().setId(orderId));
        when(orderRepository.get(orderId)).thenReturn(order);
        when(cateringMenuItemRepository.get(cateringMenuItemId))
                .thenReturn(new CateringMenuItem(new CateringMenuItemData()
                        .setId(cateringMenuItemId)
                        .setPrice(new MonetaryAmount(100))));
        eventHandler = new OrderCateringMenuItemAddedToOrderEventHandler(cateringMenuItemRepository, orderRepository);
    }

    @Test
    void canHandleEvent() {
        var event = new CateringMenuItemAddedToOrderEvent(cateringMenuItemId, 5, order);

        eventHandler.handleEvent(event);

        verify(cateringMenuItemRepository).get(cateringMenuItemId);
        verify(orderRepository).save(any(Order.class));
        assertEquals(new MonetaryAmount(500), orderRepository.get(orderId).getBillOutstandingAmount());
    }

}
