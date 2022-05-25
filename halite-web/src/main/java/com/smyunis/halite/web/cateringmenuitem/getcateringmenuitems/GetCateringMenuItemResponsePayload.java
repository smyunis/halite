package com.smyunis.halite.web.cateringmenuitem.getcateringmenuitems;

import java.net.URL;
import java.util.List;
import java.util.Set;

public class GetCateringMenuItemResponsePayload {
    private String name;
    private double price;
    private Set<String> ingredients;
    private List<URL> images;

    public String getName() {
        return name;
    }

    public GetCateringMenuItemResponsePayload setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public GetCateringMenuItemResponsePayload setPrice(double price) {
        this.price = price;
        return this;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public GetCateringMenuItemResponsePayload setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<URL> getImages() {
        return images;
    }

    public GetCateringMenuItemResponsePayload setImages(List<URL> images) {
        this.images = images;
        return this;
    }
}
