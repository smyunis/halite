package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.catererreview.domainevents.FavorableReviewGivenEvent;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private final ReviewData data;

    public Review(ReviewData data) {
        this.data = data;
    }

    public boolean isFavorable() {
        Rating rating = data.getRating();
        return rating.isFavorable();
    }

}
