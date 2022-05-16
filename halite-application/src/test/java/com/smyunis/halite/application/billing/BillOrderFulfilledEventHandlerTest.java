package com.smyunis.halite.application.billing;

import com.smyunis.halite.domain.billing.*;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.domainevents.OrderFulfilledEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillOrderFulfilledEventHandlerTest {
    private BillOrderFulfilledEventHandler handler;
    private BillRepository billRepository;
    private BillId billId;
    private CateringMenuItemRepository cateringMenuItemRepository;
    private Bill bill;

    @BeforeEach
    void setup() {
        billId = new BillId();
        billRepository = mock(BillRepository.class);

        bill = new Bill(new BillData().setId(billId));
        when(billRepository.get(billId)).thenReturn(bill);

        cateringMenuItemRepository = mock(CateringMenuItemRepository.class);
        handler = new BillOrderFulfilledEventHandler(billRepository, cateringMenuItemRepository);
    }

    @Test
    void canHandleOrderFulfilledEvent() {
        CateringMenuItemId cateringMenuItemId = new CateringMenuItemId();
        when(cateringMenuItemRepository.get(cateringMenuItemId))
                .thenReturn(new CateringMenuItem(new CateringMenuItemData().setId(cateringMenuItemId).setPrice(new MonetaryAmount(100))));

        var order = new Order(new OrderData()
                .setBillId(billId)
                .setOrderedCateringMenuItems(Map.ofEntries(
                        Map.entry(cateringMenuItemId, 1))));

        var event = new OrderFulfilledEvent(order);

        handler.handleEvent(event);

        verify(cateringMenuItemRepository).get(cateringMenuItemId);
        verify(billRepository).save(any(Bill.class));
    }
}
