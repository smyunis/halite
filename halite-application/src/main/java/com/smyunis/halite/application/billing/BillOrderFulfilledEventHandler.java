package com.smyunis.halite.application.billing;

import com.smyunis.halite.application.domaineventhandlers.DomainEventHandler;
import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillData;
import com.smyunis.halite.domain.billing.BillRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.domainevents.OrderFulfilledEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;

import java.util.Map;

public class BillOrderFulfilledEventHandler implements DomainEventHandler<OrderFulfilledEvent> {

    private final BillRepository billRepository;
    private final CateringMenuItemRepository cateringMenuItemRepository;

    public BillOrderFulfilledEventHandler(BillRepository billRepository,
                                          CateringMenuItemRepository cateringMenuItemRepository) {
        this.billRepository = billRepository;
        this.cateringMenuItemRepository = cateringMenuItemRepository;
    }

    @Override
    public void handleEvent(OrderFulfilledEvent domainEvent) {
        Order fulfilledOrder = domainEvent.getFulfilledOrder();
        Bill bill = createBill(fulfilledOrder);
        billRepository.save(bill);
    }

    private Bill createBill(Order fulfilledOrder) {
        double orderedItemsTotalPrice = getOrderedItemsTotalPrice(fulfilledOrder);
        BillData billData = new BillData()
                .setPayeeId(fulfilledOrder.getCatererId())
                .setPayerId(fulfilledOrder.getCateringEventHostId())
                .setOutstandingAmount(new MonetaryAmount(orderedItemsTotalPrice));
        return new Bill(billData);
    }

    private double getOrderedItemsTotalPrice(Order fulfilledOrder) {
        var orderedCateringMenuItems = fulfilledOrder.getOrderedCateringMenuItems();
        return orderedCateringMenuItems.keySet().stream()
                .reduce(0.0, (acc, itemId) -> {
                    double priceByQuantity = getMenuItemPriceByQuantity(orderedCateringMenuItems, itemId);
                    return acc + priceByQuantity;
                }, Double::sum);
    }

    private double getMenuItemPriceByQuantity(Map<CateringMenuItemId, Integer> orderedCateringMenuItems, CateringMenuItemId itemId) {
        var orderedMenuItem = cateringMenuItemRepository.get(itemId);
        var price = orderedMenuItem.getPrice();
        var quantity = orderedCateringMenuItems.get(itemId);
        return price.amount() * quantity;
    }
}
