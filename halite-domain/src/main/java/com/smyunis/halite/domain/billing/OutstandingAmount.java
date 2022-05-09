package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

import java.math.BigDecimal;

public record OutstandingAmount(double amount) {
    public OutstandingAmount {
        if(amount <= 0)
            throw new InvalidValueException(this.getClass().getName());
    }
}
