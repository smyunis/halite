package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenu.CateringMenuId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Caterer {
    private CatererId id = new CatererId();
    private List<DomainEvent> domainEvents = new ArrayList<>();
    private String name;
    private Set<CateringMenuId> cateringMenuIds = new HashSet<>();
    private List<Review> reviews = new ArrayList<>();

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

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

    public Set<CateringMenuId> getCateringMenus() {
        return cateringMenuIds;
    }

    void addCateringMenu(CateringMenuId cateringMenuId) {
        this.cateringMenuIds.add(cateringMenuId);
    }

    public void removeCateringMenu(CateringMenuId cateringMenuId) {
        cateringMenuIds.remove(cateringMenuId);
    }


}
