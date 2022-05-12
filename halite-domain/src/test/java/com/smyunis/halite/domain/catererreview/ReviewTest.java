package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {
    private Review review;
    private ReviewData reviewData;

    @BeforeEach
    void setup() {
        reviewData = new ReviewData();
        review = new Review(reviewData);
    }

    @Test
    void ratingIsBetween1and5() {
        assertThrows(InvalidValueException.class,() -> {
           reviewData.setRating(new Rating(6));
        });
        assertThrows(InvalidValueException.class,() -> {
            reviewData.setRating(new Rating(-1));
        });
    }

    @Test
    void isFavorableBasedOnRatingValue() {
        reviewData.setRating(new Rating(5));
        assertTrue(review.isFavorable());
    }

    @Test
    void isUnfavorableBasedOnRatingValue() {
        reviewData.setRating(new Rating(1));
        assertFalse(review.isFavorable());
    }


}
