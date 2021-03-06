package com.smyunis.halite.domain.shared;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

public record MonetaryAmount(double amount) {
    public MonetaryAmount {
        if(amount < 0)
            throw new InvalidValueException("Monetary Amount Should Not be a Negative Number");
    }
}
