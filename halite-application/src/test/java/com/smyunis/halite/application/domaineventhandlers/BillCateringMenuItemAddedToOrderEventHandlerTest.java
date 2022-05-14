package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.domain.billing.*;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillCateringMenuItemAddedToOrderEventHandlerTest {
    @Test
    void canHandleEvent() {
        BillId billId = new BillId();
        CateringMenuItemId cateringMenuItemId = new CateringMenuItemId();
        BillRepository billRepository = mock(BillRepository.class);
        CateringMenuItemRepository cateringMenuItemRepository = mock(CateringMenuItemRepository.class);
        when(billRepository.get(billId)).thenReturn(new Bill(new BillData().setId(billId)));
        when(cateringMenuItemRepository.get(cateringMenuItemId))
                .thenReturn(new CateringMenuItem(new CateringMenuItemData()
                        .setId(cateringMenuItemId)
                        .setPrice(new MonetaryAmount(100))));
        var eventHandler = new BillCateringMenuItemAddedToOrderEventHandler(cateringMenuItemRepository, billRepository);
        var order = new Order(new OrderData().setBillId(billId));
        var event = new CateringMenuItemAddedToOrderEvent(cateringMenuItemId, 5, order);

        eventHandler.handleEvent(event);

        verify(billRepository).get(billId);
        verify(cateringMenuItemRepository).get(cateringMenuItemId);
        assertEquals(new MonetaryAmount(500),billRepository.get(billId).getOutstandingAmount());
    }

}
