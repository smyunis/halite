package com.smyunis.halite.domain.cateringmenuitem;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.shared.MonetaryAmount;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CateringMenuItemDataReadOnlyProxy {
    private final CateringMenuItemData data;

    public CateringMenuItemDataReadOnlyProxy(CateringMenuItemData cateringMenuItemData) {
        this.data = cateringMenuItemData;
    }

    public CateringMenuItemId getId() {
        return data.getId();
    }

    public CatererId getCatererId() {
        return data.getCatererId();
    }

    public String getName() {
        return data.getName();
    }

    public MonetaryAmount getPrice() {
        return data.getPrice();
    }

    public Set<String> getIngredients() {
        return Collections.unmodifiableSet(data.getIngredients());
    }

    public List<URL> getImages() {
        return Collections.unmodifiableList(data.getImages());
    }
}
