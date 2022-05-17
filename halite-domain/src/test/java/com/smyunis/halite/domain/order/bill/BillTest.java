package com.smyunis.halite.domain.order.bill;

import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BillTest {
    private BillData data;
    private Bill bill;

    @BeforeEach
    void setup() {
        data = new BillData();
        bill = new Bill(data);
    }

    @Test
    void outStandingBillAmountIsAValidPositiveNumber() {
        assertThrows(InvalidValueException.class, () -> {
            data.setOutstandingAmount(new MonetaryAmount(-1.00));
        });
        assertDoesNotThrow(() -> {
            data.setOutstandingAmount(new MonetaryAmount(1000.00));
        });
    }

    @Test
    void canSettleBill() {
        data.setOutstandingAmount(new MonetaryAmount(1000));

        bill.settle();
        assertEquals(BillStatus.SETTLED, bill.getBillStatus());
    }

    @Test
    void canNotSettleBillWhoseDueDateHasPassed() {
        data.setDueDateTime(LocalDateTime.now().minusDays(2));

        assertThrows(InvalidOperationException.class, bill::settle);
    }

    @Test
    void canCancelBill() {
        bill.requestCancellation();
        bill.approveCancellation();

        assertEquals(BillStatus.CANCELLED, bill.getBillStatus());
    }

    @Test
    void canNotCancelBillThatHasNotBeenSettledAlready() {
        data.setOutstandingAmount(new MonetaryAmount(1000));

        bill.settle();

        assertThrows(InvalidOperationException.class, () -> {
            bill.approveCancellation();
        });
    }

    @Test
    void canNotSettleBillThatIsPendingCancellation() {
        bill.requestCancellation();
        assertThrows(InvalidOperationException.class,() -> {
           bill.settle();
        });
    }

    @Test
    void canNotSettleABillThatWasCanceled() {
        bill.requestCancellation();
        bill.approveCancellation();

        assertThrows(InvalidOperationException.class, () -> {
            bill.settle();
        });
    }

    @Test
    void canNotSettleAnAlreadySettledBill() {
        bill.settle();

        assertThrows(InvalidOperationException.class, () -> {
            bill.settle();
        });
    }

    @Test
    void incrementOutstandingAmount() {
        data.setOutstandingAmount(new MonetaryAmount(10));
        MonetaryAmount initialValue = bill.getOutstandingAmount();
        bill.incrementOutstandingAmount(new MonetaryAmount(10.0));

        assertEquals(initialValue.amount() + 10, bill.getOutstandingAmount().amount());
    }

    @Test
    void decrementOutstandingAmount() {
        data.setOutstandingAmount(new MonetaryAmount(10));
        MonetaryAmount initialValue = bill.getOutstandingAmount();

        bill.decrementOutstandingAmount(new MonetaryAmount(5));

        assertEquals(initialValue.amount() - 5,bill.getOutstandingAmount().amount());
    }


}
