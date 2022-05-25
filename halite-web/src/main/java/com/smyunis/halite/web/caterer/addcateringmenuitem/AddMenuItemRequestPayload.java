package com.smyunis.halite.web.caterer.addcateringmenuitem;

import java.net.URL;
import java.util.List;
import java.util.Set;

public class AddMenuItemRequestPayload {
    private String name;
    private double price;
    private Set<String> ingredients;
    private List<URL> images;

    public String getName() {
        return name;
    }

    public AddMenuItemRequestPayload setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public AddMenuItemRequestPayload setPrice(double price) {
        this.price = price;
        return this;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public AddMenuItemRequestPayload setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<URL> getImages() {
        return images;
    }

    public AddMenuItemRequestPayload setImages(List<URL> images) {
        this.images = images;
        return this;
    }
}
