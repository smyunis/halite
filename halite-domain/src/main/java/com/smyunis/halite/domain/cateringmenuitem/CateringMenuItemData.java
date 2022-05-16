package com.smyunis.halite.domain.cateringmenuitem;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.shared.MonetaryAmount;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CateringMenuItemData {
    private CateringMenuItemId id = new CateringMenuItemId();
    private CatererId catererId;
    private String name;
    private MonetaryAmount price;
    private Set<String> ingredients;
    private List<URL> images;

    public CateringMenuItemId getId() {
        return id;
    }

    public CateringMenuItemData setId(CateringMenuItemId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CateringMenuItemData setName(String name) {
        this.name = name;
        return this;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public CateringMenuItemData setPrice(MonetaryAmount price) {
        this.price = price;
        return this;
    }

    public Set<String> getIngredients() {
        return Collections.unmodifiableSet(ingredients);
    }

    public CateringMenuItemData setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<URL> getImages() {
        return Collections.unmodifiableList(images);
    }

    public CateringMenuItemData setImages(List<URL> images) {
        this.images = images;
        return this;
    }

    public CatererId getCatererId() {
        return catererId;
    }

    public CateringMenuItemData setCatererId(CatererId catererId) {
        this.catererId = catererId;
        return this;
    }
}