package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BillTest {
    private BillData billData;
    private Bill bill;

    @BeforeEach
    void setup() {
        billData = new BillData();
        bill = new Bill(billData);
    }

    @Test
    void outStandingBillAmountIsAValidPositiveNumber() {
        assertThrows(InvalidValueException.class, () -> {
            billData.setOutstandingAmount(new OutstandingAmount(0.00));
        });
        assertDoesNotThrow(() -> {
            billData.setOutstandingAmount(new OutstandingAmount(1000.00));
        });
    }

    @Test
    void canSettleBill() {
        billData.setOutstandingAmount(new OutstandingAmount(1000));

        bill.settle();
        assertEquals(BillStatus.SETTLED, bill.getBillStatus());
    }

    @Test
    void canNotSettleBillWhoseDueDateHasPassed() {
        billData.setDueDateTime(LocalDateTime.now().minusDays(2));

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
        billData.setOutstandingAmount(new OutstandingAmount(1000));

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
    void emitsBillSettledEvent() {
        billData.setOutstandingAmount(new OutstandingAmount(1000));

        bill.settle();

        List<DomainEvent> domainEvents = bill.getDomainEvents();
        assertTrue(domainEvents.get(0) instanceof BillSettledEvent);
    }


}
