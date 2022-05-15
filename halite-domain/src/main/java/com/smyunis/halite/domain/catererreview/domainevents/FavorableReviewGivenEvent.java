package com.smyunis.halite.domain.catererreview.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.catererreview.Review;

public class FavorableReviewGivenEvent extends DomainEvent {
    private final Review review;

    public FavorableReviewGivenEvent(Review review) {
        this.review = review;
    }

    public Review getReview() {
        return review;
    }
}
