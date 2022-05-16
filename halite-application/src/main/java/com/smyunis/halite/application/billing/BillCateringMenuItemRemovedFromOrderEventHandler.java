package com.smyunis.halite.application.billing;

import com.smyunis.halite.application.domaineventhandlers.DomainEventHandler;
import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.BillRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemRemovedFromOrderEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;

public class BillCateringMenuItemRemovedFromOrderEventHandler implements DomainEventHandler<CateringMenuItemRemovedFromOrderEvent> {
    private final CateringMenuItemRepository cateringMenuItemRepository;
    private final BillRepository billRepository;

    public BillCateringMenuItemRemovedFromOrderEventHandler(CateringMenuItemRepository cateringMenuItemRepository, BillRepository billRepository) {
        this.cateringMenuItemRepository = cateringMenuItemRepository;
        this.billRepository = billRepository;
    }

    @Override
    public void handleEvent(CateringMenuItemRemovedFromOrderEvent domainEvent) {
        Bill billToBeUpdated = getBillToBeUpdated(domainEvent);
        MonetaryAmount amountToDecrease = getAmountToDecrease(domainEvent);
        billToBeUpdated.decrementOutstandingAmount(amountToDecrease);
        billRepository.save(billToBeUpdated);
    }

    private MonetaryAmount getAmountToDecrease(CateringMenuItemRemovedFromOrderEvent domainEvent) {
        CateringMenuItemId removedItemId = domainEvent.getItemId();
        CateringMenuItem removedCateringItem = cateringMenuItemRepository.get(removedItemId);
        return new MonetaryAmount(removedCateringItem.getPrice().amount() * domainEvent.getRemovedItemQuantity());
    }

    private Bill getBillToBeUpdated(CateringMenuItemRemovedFromOrderEvent domainEvent) {
        Order orderWhoHadAnItemRemoved = domainEvent.getOrder();
        return getBillToBeUpdated(orderWhoHadAnItemRemoved);
    }
    private Bill getBillToBeUpdated(Order orderWhoHadAnItemRemoved) {
        BillId billId = orderWhoHadAnItemRemoved.getBillId();
        return billRepository.get(billId);
    }
}
