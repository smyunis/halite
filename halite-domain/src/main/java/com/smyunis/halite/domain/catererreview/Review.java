package com.smyunis.halite.domain.catererreview;

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
