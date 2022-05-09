package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BillTest {
    private Bill bill;

    @BeforeEach
    void setup() {
        bill = new Bill();
    }
    @Test
    void outStandingBillAmountIsAValidPositiveNumber () {
        assertThrows(InvalidValueException.class,() -> {
            bill.setOutstandingAmount(new OutstandingAmount(0.00));
        });
        assertDoesNotThrow(() -> {
            bill.setOutstandingAmount(new OutstandingAmount(1500.00));
        });
    }
}
