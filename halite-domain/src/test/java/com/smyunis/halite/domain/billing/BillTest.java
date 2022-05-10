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
        assertEquals(BillStatus.Settled, bill.getStatus());
    }

    @Test
    void canNotSettleBillWhoseDueDateHasPassed() {
        bill.setDueDate(LocalDateTime.now().minusDays(2));
        assertThrows(InvalidOperationException.class, () -> {
            bill.settle();
        });
    }

    @Test
    void canCancelBill() {
        bill.cancel();
        assertEquals(BillStatus.Cancelled, bill.getStatus());
    }

    @Test
    void canNotCancelBillThatHasNotBeenSettledAlready() {
        bill.setDueDate(LocalDateTime.now().plusDays(2));
        bill.settle();

        assertThrows(InvalidOperationException.class, () -> {
            bill.cancel();
        });
    }

    @Test
    void canNotSettleABillThatWasCanceled() {
        bill.setDueDate(LocalDateTime.now().plusDays(2));
        bill.cancel();

        assertThrows(InvalidOperationException.class, () -> {
            bill.settle();
        });
    }

    @Test
    void canNotSettleAnAlreadySettledBill() {
        bill.setDueDate(LocalDateTime.now().plusDays(2));
        bill.settle();

        assertThrows(InvalidOperationException.class, () -> {
            bill.settle();
        });
    }

    @Test
    void canSetAndGetRemarkAboutBill() {
        String remark = "Transaction Id: 96sd8954814r";
        bill.addRemark(remark);

        assertEquals(remark,bill.getRemark());
    }

    @Test
    void emitsBillSettledEvent () {
        bill.setDueDate(LocalDateTime.now().plusDays(2));
        bill.settle();
        List<DomainEvent> domainEvents = bill.getDomainEvents();
        assertTrue(domainEvents.get(0) instanceof BillSettledEvent);
    }


}
