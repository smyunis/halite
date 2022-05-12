package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

public class Rating {
    private final int maxRating = 5;
    private final int minRating = 1;
    private final int rating;

    public Rating(int rating) {
        if (rating < minRating || rating > maxRating)
            throw new InvalidValueException(this.getClass().getName());
        this.rating = rating;
    }

    public boolean isFavorable() {
        int averageRating = (maxRating + minRating) / 2;
        return rating > averageRating;
    }
}
