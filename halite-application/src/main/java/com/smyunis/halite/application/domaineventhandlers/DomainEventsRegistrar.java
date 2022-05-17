package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.application.caterer.CatererBillSettledEventHandler;
import com.smyunis.halite.application.order.OrderCateringMenuItemRemovedEventHandler;
import com.smyunis.halite.application.order.bill.OrderCateringMenuItemAddedToOrderEventHandler;
import com.smyunis.halite.application.order.bill.OrderCateringMenuItemRemovedFromOrderEventHandler;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.cateringmenuitem.domainevents.CateringMenuItemRemovedEvent;
import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.domain.order.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemRemovedFromOrderEvent;

public class DomainEventsRegistrar {
    private final DomainEventDispatcher domainEventManager;
    private final CatererRepository catererRepository;
    private final CateringMenuItemRepository cateringMenuItemRepository;
    private final OrderRepository orderRepository;

    public DomainEventsRegistrar(
            DomainEventDispatcher domainEventManager,
            CatererRepository catererRepository,
            CateringMenuItemRepository cateringMenuItemRepository,
            OrderRepository orderRepository) {
        this.domainEventManager = domainEventManager;
        this.catererRepository = catererRepository;
        this.cateringMenuItemRepository = cateringMenuItemRepository;
        this.orderRepository = orderRepository;
    }

    public void assignHandlersForDomainEvents() {
        domainEventManager.assignHandler(BillSettledEvent.class, new CatererBillSettledEventHandler(catererRepository));
        domainEventManager.assignHandler(CateringMenuItemAddedToOrderEvent.class,
                new OrderCateringMenuItemAddedToOrderEventHandler(cateringMenuItemRepository, orderRepository));
        domainEventManager.assignHandler(CateringMenuItemRemovedFromOrderEvent.class,
                new OrderCateringMenuItemRemovedFromOrderEventHandler(cateringMenuItemRepository, orderRepository));
        domainEventManager.assignHandler(CateringMenuItemRemovedEvent.class,
                new OrderCateringMenuItemRemovedEventHandler(orderRepository));
    }
}
