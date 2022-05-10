package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BillTest {
    private Bill bill;

    @BeforeEach
    void setup() {
        bill = new Bill();
    }

    @Test
    void outStandingBillAmountIsAValidPositiveNumber() {
        assertThrows(InvalidValueException.class, () -> {
            bill.setOutstandingAmount(new OutstandingAmount(0.00));
        });
        assertDoesNotThrow(() -> {
            bill.setOutstandingAmount(new OutstandingAmount(1500.00));
        });
    }

    @Test
    void canSettleBill() {
        bill.setDueDate(LocalDateTime.now().plusDays(2));
        bill.settle();
        assertEquals(BillStatus.Paid, bill.getStatus());
    }

    @Test
    void canNotSettleBillWhoseDueDateHasPassed() {
        bill.setDueDate(LocalDateTime.now().minusDays(2));
        assertThrows(InvalidOperationException.class, () -> {
            bill.settle();
        });
    }


}
