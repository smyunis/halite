package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.BillRepository;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;

public class BillCateringMenuItemAddedToOrderEventHandler implements DomainEventHandler<CateringMenuItemAddedToOrderEvent> {

    private final CateringMenuItemRepository cateringMenuItemRepository;
    private final BillRepository billRepository;

    public BillCateringMenuItemAddedToOrderEventHandler(CateringMenuItemRepository cateringMenuItemRepository, BillRepository billRepository) {
        this.cateringMenuItemRepository = cateringMenuItemRepository;
        this.billRepository = billRepository;
    }

    @Override
    public void handleEvent(CateringMenuItemAddedToOrderEvent domainEvent) {
        Bill billToBeUpdated = getBillToBeUpdated(domainEvent);
        incrementOutstandingBillAmount(domainEvent, billToBeUpdated);
    }

    private Bill getBillToBeUpdated(CateringMenuItemAddedToOrderEvent domainEvent) {
        Order orderWhoHadAnItemAdded = domainEvent.getOrder();
        return getBillToBeUpdated(orderWhoHadAnItemAdded);
    }

    private void incrementOutstandingBillAmount(CateringMenuItemAddedToOrderEvent event, Bill billToBeUpdated) {
        CateringMenuItemId addedItemId = event.getAddedItemId();
        Integer addedQuantity = event.getAddedItemQuantity();
        CateringMenuItem addedItem = cateringMenuItemRepository.get(addedItemId);
        billToBeUpdated.incrementOutstandingAmount(new MonetaryAmount(addedItem.getPrice().amount() * addedQuantity));
    }

    private Bill getBillToBeUpdated(Order orderWhoHadAnItemAdded) {
        BillId billId = orderWhoHadAnItemAdded.getBillId();
        return billRepository.get(billId);
    }
}
