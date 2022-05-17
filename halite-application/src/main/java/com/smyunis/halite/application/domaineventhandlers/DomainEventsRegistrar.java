package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.application.billing.BillCateringMenuItemAddedToOrderEventHandler;
import com.smyunis.halite.application.billing.BillCateringMenuItemRemovedFromOrderEventHandler;
import com.smyunis.halite.application.billing.BillOrderCreatedEventHandler;
import com.smyunis.halite.application.caterer.CatererBillSettledEventHandler;
import com.smyunis.halite.application.order.OrderCateringMenuItemRemovedEventHandler;
import com.smyunis.halite.domain.billing.BillRepository;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.cateringmenuitem.domainevents.CateringMenuItemRemovedEvent;
import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemRemovedFromOrderEvent;
import com.smyunis.halite.domain.order.domainevents.OrderCreatedEvent;

public class DomainEventsRegistrar {
    private final DomainEventDispatcher domainEventManager;
    private final CatererRepository catererRepository;
    private final CateringMenuItemRepository cateringMenuItemRepository;
    private final BillRepository billRepository;
    private final OrderRepository orderRepository;

    public DomainEventsRegistrar(
            DomainEventDispatcher domainEventManager,
            CatererRepository catererRepository,
            CateringMenuItemRepository cateringMenuItemRepository,
            BillRepository billRepository,
            OrderRepository orderRepository) {
        this.domainEventManager = domainEventManager;
        this.catererRepository = catererRepository;
        this.cateringMenuItemRepository = cateringMenuItemRepository;
        this.billRepository = billRepository;
        this.orderRepository = orderRepository;
    }

    public void assignHandlersForDomainEvents() {
        domainEventManager.assignHandler(BillSettledEvent.class, new CatererBillSettledEventHandler(catererRepository));
        domainEventManager.assignHandler(CateringMenuItemAddedToOrderEvent.class,
                new BillCateringMenuItemAddedToOrderEventHandler(cateringMenuItemRepository, billRepository));
        domainEventManager.assignHandler(CateringMenuItemRemovedFromOrderEvent.class,
                new BillCateringMenuItemRemovedFromOrderEventHandler(cateringMenuItemRepository,billRepository));
        domainEventManager.assignHandler(CateringMenuItemRemovedEvent.class,
                new OrderCateringMenuItemRemovedEventHandler(orderRepository));
        domainEventManager.assignHandler(OrderCreatedEvent.class,new BillOrderCreatedEventHandler(billRepository));
    }
}
