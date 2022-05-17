package com.smyunis.halite.application.order.bill;

import com.smyunis.halite.application.domaineventhandlers.DomainEventHandler;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;

public class OrderCateringMenuItemAddedToOrderEventHandler implements DomainEventHandler<CateringMenuItemAddedToOrderEvent> {

    private final CateringMenuItemRepository cateringMenuItemRepository;
    private final OrderRepository orderRepository;

    public OrderCateringMenuItemAddedToOrderEventHandler(CateringMenuItemRepository cateringMenuItemRepository,
                                                         OrderRepository orderRepository) {
        this.cateringMenuItemRepository = cateringMenuItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void handleEvent(CateringMenuItemAddedToOrderEvent domainEvent) {
        Order orderWhoHadAnItemAdded = domainEvent.getOrder();
        incrementOutstandingBillAmount(domainEvent, orderWhoHadAnItemAdded);
        orderRepository.save(orderWhoHadAnItemAdded);
    }

    private void incrementOutstandingBillAmount(CateringMenuItemAddedToOrderEvent event, Order orderWhoHadAnItemAdded) {
        MonetaryAmount amountToIncrement = getAmountToIncrement(event);
        orderWhoHadAnItemAdded.incrementBillOutstandingAmount(amountToIncrement);
    }

    private MonetaryAmount getAmountToIncrement(CateringMenuItemAddedToOrderEvent event) {
        CateringMenuItemId addedItemId = event.getAddedItemId();
        Integer addedQuantity = event.getAddedItemQuantity();
        CateringMenuItem addedItem = cateringMenuItemRepository.get(addedItemId);
        return new MonetaryAmount(addedItem.getPrice().amount() * addedQuantity);
    }

}
