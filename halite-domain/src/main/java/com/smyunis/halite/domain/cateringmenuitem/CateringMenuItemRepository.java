package com.smyunis.halite.domain.cateringmenuitem;

import com.smyunis.halite.domain.DomainEntityRepository;
import com.smyunis.halite.domain.caterer.CatererId;

import java.util.List;

public interface CateringMenuItemRepository extends DomainEntityRepository<CateringMenuItem,CateringMenuItemId> {
    void remove(CateringMenuItemId id);

    List<CateringMenuItem> getAllByCatererId(CatererId catererId);
}
