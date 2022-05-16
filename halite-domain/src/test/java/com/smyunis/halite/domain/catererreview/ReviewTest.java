package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.catererreview.domainevents.FavorableReviewGivenEvent;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {
    private Review review;
    private ReviewData data;

    @BeforeEach
    void setup() {
        data = new ReviewData();
        review = new Review(data);
    }

    @Test
    void ratingIsBetween1and5() {
        assertThrows(InvalidValueException.class,() -> {
           data.setRating(new Rating(6));
        });
        assertThrows(InvalidValueException.class,() -> {
            data.setRating(new Rating(-1));
        });
    }

    @Test
    void isFavorableBasedOnRatingValue() {
        data.setRating(new Rating(5));
        assertTrue(review.isFavorable());
    }

    @Test
    void isUnfavorableBasedOnRatingValue() {
        data.setRating(new Rating(1));
        assertFalse(review.isFavorable());
    }



}
