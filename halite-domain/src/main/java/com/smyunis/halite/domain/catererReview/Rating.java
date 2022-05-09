package com.smyunis.halite.domain.catererReview;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

public record Rating (int rating) {
    public Rating {
        if(rating < 1 || rating > 5)
            throw new InvalidValueException(this.getClass().getName());
    }
}
