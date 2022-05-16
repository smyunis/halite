package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.DomainEntityRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;

public interface OrderRepository extends DomainEntityRepository<Order> {
    void removeMenuItemFromAllOrdersPendingAcceptanceByMenuItemId(CateringMenuItemId cateringMenuItemId);
}
