package com.smyunis.halite.application.billing;

import com.smyunis.halite.application.domaineventhandlers.DomainEventHandler;
import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillData;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.BillRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.domainevents.OrderFulfilledEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;

import java.util.Map;

public class BillOrderFulfilledEventHandler implements DomainEventHandler<OrderFulfilledEvent> {

    private final BillRepository billRepository;
    private final CateringMenuItemRepository cateringMenuItemRepository;

    public BillOrderFulfilledEventHandler(BillRepository billRepository, CateringMenuItemRepository cateringMenuItemRepository) {
        this.billRepository = billRepository;
        this.cateringMenuItemRepository = cateringMenuItemRepository;
    }

    @Override
    public void handleEvent(OrderFulfilledEvent domainEvent) {
        Order fulfilledOrder = domainEvent.getFulfilledOrder();
        var orderedCateringMenuItems = fulfilledOrder.getOrderedCateringMenuItems();

        double orderedItemsPriceTotal = orderedCateringMenuItems.keySet().stream()
                .reduce(0.0, (acc, itemId) -> {
                    var orderedMenuItem = cateringMenuItemRepository.get(itemId);
                    var price = orderedMenuItem.getPrice();
                    var quantity = orderedCateringMenuItems.get(itemId);
                    var priceByQuantity = price.amount() * quantity;
                    return acc + priceByQuantity;
                }, Double::sum);

        BillData billData = new BillData();
        billData.setPayeeId(fulfilledOrder.getCatererId())
                .setPayerId(fulfilledOrder.getCateringEventHostId())
                .setOutstandingAmount(new MonetaryAmount(orderedItemsPriceTotal));
        Bill bill = new Bill(billData);
        billRepository.save(bill);
    }
}
