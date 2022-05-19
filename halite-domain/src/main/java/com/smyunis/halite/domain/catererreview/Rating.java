package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

import java.util.Objects;

public class Rating {
    private final int maxRating = 5;
    private final int minRating = 1;
    private final int rating;

    public Rating(int rating) {
        assertRatingIsBetweenMinAndMax(rating);
        this.rating = rating;
    }

    private void assertRatingIsBetweenMinAndMax(int rating) {
        if (rating < minRating || rating > maxRating)
            throw new InvalidValueException(this.getClass().getName());
    }

    public boolean isFavorable() {
        int averageRating = (maxRating + minRating) / 2;
        return rating > averageRating;
    }

    public int toInt() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return rating == rating1.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating);
    }
}
