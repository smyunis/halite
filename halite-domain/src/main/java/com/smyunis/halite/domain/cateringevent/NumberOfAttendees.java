package com.smyunis.halite.domain.cateringevent;

import com.smyunis.halite.domain.InvalidValueException;

public record NumberOfAttendees (int numberOfAttendees) {
    public NumberOfAttendees {
        if(numberOfAttendees < 1)
            throw new InvalidValueException(this.getClass().getName());
    }
}
