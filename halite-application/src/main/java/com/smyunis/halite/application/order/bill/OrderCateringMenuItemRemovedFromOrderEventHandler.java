package com.smyunis.halite.application.order.bill;

import com.smyunis.halite.application.domaineventhandlers.DomainEventHandler;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemRemovedFromOrderEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;

public class OrderCateringMenuItemRemovedFromOrderEventHandler implements DomainEventHandler<CateringMenuItemRemovedFromOrderEvent> {
    private final CateringMenuItemRepository cateringMenuItemRepository;
    private final OrderRepository orderRepository;

    public OrderCateringMenuItemRemovedFromOrderEventHandler(CateringMenuItemRepository cateringMenuItemRepository,
                                                             OrderRepository orderRepository) {
        this.cateringMenuItemRepository = cateringMenuItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void handleEvent(CateringMenuItemRemovedFromOrderEvent domainEvent) {
        Order orderWhoseBillWillBeUpdated = getOrderWhoseBillWillBeUpdated(domainEvent);
        MonetaryAmount amountToDecrease = getAmountToDecrease(domainEvent);
        orderWhoseBillWillBeUpdated.decrementBillOutstandingAmount(amountToDecrease);
        orderRepository.save(orderWhoseBillWillBeUpdated);
    }

    private Order getOrderWhoseBillWillBeUpdated(CateringMenuItemRemovedFromOrderEvent domainEvent) {
        return orderRepository.get(domainEvent.getOrder().getId());
    }

    private MonetaryAmount getAmountToDecrease(CateringMenuItemRemovedFromOrderEvent domainEvent) {
        CateringMenuItemId removedItemId = domainEvent.getItemId();
        CateringMenuItem removedCateringItem = cateringMenuItemRepository.get(removedItemId);
        return new MonetaryAmount(removedCateringItem.getPrice().amount() * domainEvent.getRemovedItemQuantity());
    }


}
