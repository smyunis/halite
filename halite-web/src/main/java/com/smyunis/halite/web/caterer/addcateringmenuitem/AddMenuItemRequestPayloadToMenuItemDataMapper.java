package com.smyunis.halite.web.caterer.addcateringmenuitem;

import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import com.smyunis.halite.web.Mapper;

public class AddMenuItemRequestPayloadToMenuItemDataMapper
        implements Mapper<AddMenuItemRequestPayload, CateringMenuItemData> {

    @Override
    public CateringMenuItemData map(AddMenuItemRequestPayload obj) {
        return new CateringMenuItemData()
                .setIngredients(obj.getIngredients())
                .setImages(obj.getImages())
                .setPrice(new MonetaryAmount(obj.getPrice()))
                .setName(obj.getName());
    }
}
