package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.DomainEntityRepository;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

public interface OrderRepository extends DomainEntityRepository<Order,OrderId> {
    void removeMenuItemFromAllOrdersPendingAcceptanceByMenuItemId(CateringMenuItemId cateringMenuItemId);
    void cancelAllOrdersByCateringEventId(CateringEventId cateringEventId);
}
