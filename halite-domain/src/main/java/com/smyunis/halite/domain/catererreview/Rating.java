package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

public record Rating (int rating) {
    public Rating {
        if(rating < 1 || rating > 5)
            throw new InvalidValueException(this.getClass().getName());
    }
}
