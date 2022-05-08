package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringmenu.CateringMenuItemId;

import java.util.List;

public class Order {
    private OrderId id;
    private CateringEventId cateringEventId;
    private CateringEventHostId cateringEventHostId;
    private CatererId catererId;
    private List<CateringMenuItemId> cateringMenuItems;
}
