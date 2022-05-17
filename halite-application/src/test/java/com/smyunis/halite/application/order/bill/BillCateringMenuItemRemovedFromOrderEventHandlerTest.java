package com.smyunis.halite.application.order.bill;

import com.smyunis.halite.domain.order.OrderId;
import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.domain.order.bill.Bill;
import com.smyunis.halite.domain.order.bill.BillData;
import com.smyunis.halite.domain.order.bill.BillId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemRemovedFromOrderEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillCateringMenuItemRemovedFromOrderEventHandlerTest {

    private CateringMenuItemId cateringMenuItemId;

    private CateringMenuItemRepository cateringMenuItemRepository;
    private OrderCateringMenuItemRemovedFromOrderEventHandler eventHandler;
    private OrderRepository orderRepository;
    private OrderId orderId;

    @BeforeEach
    void setup() {
        orderId = new OrderId();
        cateringMenuItemId = new CateringMenuItemId();
        orderRepository = mock(OrderRepository.class);
        cateringMenuItemRepository = mock(CateringMenuItemRepository.class);
        Order order = new Order(new OrderData().setId(orderId));
        order.incrementBillOutstandingAmount(new MonetaryAmount(1000));

        when(orderRepository.get(orderId)).thenReturn(order);
        when(cateringMenuItemRepository.get(cateringMenuItemId))
                .thenReturn(new CateringMenuItem(new CateringMenuItemData()
                        .setId(cateringMenuItemId)
                        .setPrice(new MonetaryAmount(100))));
        eventHandler = new OrderCateringMenuItemRemovedFromOrderEventHandler(cateringMenuItemRepository, orderRepository);
    }

    @Test
    void canHandleCateringMenuItemRemovedFromOrderEvent() {

        var orderedMenuItems = new HashMap<CateringMenuItemId,Integer>();
        orderedMenuItems.put(cateringMenuItemId,10);

        var order = new Order(new OrderData()
                .setId(orderId)
                .setOrderedCateringMenuItems(orderedMenuItems));

        order.removeCateringMenuItem(cateringMenuItemId, 5);


        var event = new CateringMenuItemRemovedFromOrderEvent(cateringMenuItemId, 5, order);
        eventHandler.handleEvent(event);

        verify(orderRepository).get(orderId);
        verify(cateringMenuItemRepository).get(cateringMenuItemId);

        assertEquals(new MonetaryAmount(500), orderRepository.get(orderId).getBillOutstandingAmount());
    }
}
