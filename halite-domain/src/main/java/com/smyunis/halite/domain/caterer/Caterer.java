package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.cateringmenu.CateringMenuId;

import java.util.ArrayList;
import java.util.List;

public class Caterer {
    private CatererId id = new CatererId();
    private String name;
    private List<CateringMenuId> cateringMenuIds;
    private List<Review> reviews = new ArrayList<>();

    public CatererId getId() {
        return id;
    }

    void setId(CatererId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<CateringMenuId> getCateringMenus() {
        return cateringMenuIds;
    }

    void addCateringMenu(CateringMenuId cateringMenuId) {
        this.cateringMenuIds.add(cateringMenuId);
    }


}
