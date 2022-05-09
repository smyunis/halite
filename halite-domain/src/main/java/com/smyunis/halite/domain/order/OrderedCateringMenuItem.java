package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

public record OrderedCateringMenuItem (CateringMenuItemId cateringMenuItemId, int quantity){
    public OrderedCateringMenuItem {
        if(quantity < 1)
            throw new InvalidValueException(this.getClass().getName());
    }

    public OrderedCateringMenuItem(CateringMenuItemId cateringMenuItemId) {
        this(cateringMenuItemId, 1);
    }
}
