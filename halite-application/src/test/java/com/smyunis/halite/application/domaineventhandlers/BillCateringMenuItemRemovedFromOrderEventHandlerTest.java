package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillData;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.BillRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemRemovedFromOrderEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillCateringMenuItemRemovedFromOrderEventHandlerTest {

    private BillId billId;
    private CateringMenuItemId cateringMenuItemId;
    private BillRepository billRepository;
    private CateringMenuItemRepository cateringMenuItemRepository;
    private BillCateringMenuItemRemovedFromOrderEventHandler eventHandler;

    @BeforeEach
    void setup() {
        billId = new BillId();
        cateringMenuItemId = new CateringMenuItemId();
        billRepository = mock(BillRepository.class);
        cateringMenuItemRepository = mock(CateringMenuItemRepository.class);
        when(billRepository.get(billId)).thenReturn(new Bill(new BillData().setId(billId).setOutstandingAmount(new MonetaryAmount(1000))));
        when(cateringMenuItemRepository.get(cateringMenuItemId))
                .thenReturn(new CateringMenuItem(new CateringMenuItemData()
                        .setId(cateringMenuItemId)
                        .setPrice(new MonetaryAmount(100))));
        eventHandler = new BillCateringMenuItemRemovedFromOrderEventHandler(cateringMenuItemRepository, billRepository);
    }

    @Test
    void canHandleCateringMenuItemRemovedFromOrderEvent() {

        var orderedMenuItems = new HashMap<CateringMenuItemId,Integer>();
        orderedMenuItems.put(cateringMenuItemId,10);

        var order = new Order(new OrderData()
                .setBillId(billId)
                .setOrderedCateringMenuItems(orderedMenuItems));

        order.removeCateringMenuItem(cateringMenuItemId, 5);


        var event = new CateringMenuItemRemovedFromOrderEvent(cateringMenuItemId, 5, order);
        eventHandler.handleEvent(event);

        verify(billRepository).get(billId);
        verify(cateringMenuItemRepository).get(cateringMenuItemId);

        assertEquals(new MonetaryAmount(500), billRepository.get(billId).getOutstandingAmount());
    }
}
