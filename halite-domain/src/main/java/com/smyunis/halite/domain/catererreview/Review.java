package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.catererreview.domainevents.FavorableReviewGivenEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Review {
    private final ReviewData data;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Review(ReviewData data) {
        this.data = data;
    }

    public boolean isFavorable() {
        Rating rating = data.getRating();
        return rating.isFavorable();
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public Review asNewReview() {
        if (this.isFavorable()) {
            domainEvents.add(new FavorableReviewGivenEvent(this));
        }
        return this;
    }

    public CatererId getReviewedCatererId() {
        return data.getReviewedCatererId();
    }

    public ReviewDataReadOnlyProxy getDataReadOnlyProxy() {
        return new ReviewDataReadOnlyProxy(data);
    }

}
