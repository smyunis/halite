package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.application.billing.BillCateringMenuItemAddedToOrderEventHandler;
import com.smyunis.halite.application.caterer.CatererBillSettledEventHandler;
import com.smyunis.halite.domain.billing.BillRepository;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;

public class DomainEventsRegistrar {
    private final DomainEventDispatcher domainEventManager;
    private final CatererRepository catererRepository;
    private final CateringMenuItemRepository cateringMenuItemRepository;
    private final BillRepository billRepository;

    public DomainEventsRegistrar(
            DomainEventDispatcher domainEventManager,
            CatererRepository catererRepository,
            CateringMenuItemRepository cateringMenuItemRepository,
            BillRepository billRepository) {
        this.domainEventManager = domainEventManager;
        this.catererRepository = catererRepository;
        this.cateringMenuItemRepository = cateringMenuItemRepository;
        this.billRepository = billRepository;
    }

    public void assignHandlersForDomainEvents() {
        domainEventManager.assignHandler(BillSettledEvent.class, new CatererBillSettledEventHandler(catererRepository));
        domainEventManager.assignHandler(CateringMenuItemAddedToOrderEvent.class,
                new BillCateringMenuItemAddedToOrderEventHandler(cateringMenuItemRepository,billRepository));
    }
}
