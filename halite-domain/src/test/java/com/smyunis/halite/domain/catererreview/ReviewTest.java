package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReviewTest {
    private Review review;

    @BeforeEach
    void setup() {
        review = new Review();
    }

    @Test
    void ratingIsBetween1and5() {
        assertThrows(InvalidValueException.class,() -> {
           review.setRating(new Rating(6));
        });
        assertThrows(InvalidValueException.class,() -> {
            review.setRating(new Rating(-1));
        });
    }
}
