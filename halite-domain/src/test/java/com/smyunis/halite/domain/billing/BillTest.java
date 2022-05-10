package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BillTest {

    @Test
    void outStandingBillAmountIsAValidPositiveNumber() {
        assertThrows(InvalidValueException.class, () -> {
            Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(0.00));
        });
        assertDoesNotThrow(() -> {
            Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(1500.00));
        });
    }

    @Test
    void canSettleBill() {
        Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(1000.00));

        bill.settle();
        assertEquals(BillStatus.Settled, bill.getStatus());
    }

    @Test
    @Disabled("setDueDate should not be exposed")
    void canNotSettleBillWhoseDueDateHasPassed() {
        Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(1000.00));
        //bill.setDueDate(LocalDateTime.now().minusDays(2));

        assertThrows(InvalidOperationException.class, () -> {
            bill.settle();
        });
    }

    @Test
    void canCancelBill() {
        Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(1000.00));
        bill.cancel();
        assertEquals(BillStatus.Cancelled, bill.getStatus());
    }

    @Test
    void canNotCancelBillThatHasNotBeenSettledAlready() {
        Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(1000.00));
        bill.settle();

        assertThrows(InvalidOperationException.class, () -> {
            bill.cancel();
        });
    }

    @Test
    void canNotSettleABillThatWasCanceled() {
        Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(1000.00));
        bill.cancel();

        assertThrows(InvalidOperationException.class, () -> {
            bill.settle();
        });
    }

    @Test
    void canNotSettleAnAlreadySettledBill() {
        Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(1000.00));

        bill.settle();

        assertThrows(InvalidOperationException.class, () -> {
            bill.settle();
        });
    }

    @Test
    void canSetAndGetRemarkAboutBill() {
        Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(1000.00));
        String remark = "Transaction Id: 96sd8954814r";
        bill.addRemark(remark);

        assertEquals(remark, bill.getRemark());
    }

    @Test
    void emitsBillSettledEvent() {
        Bill bill = new Bill(new BillId(),null,null,null,new OutstandingAmount(1000.00));

        bill.settle();

        List<DomainEvent> domainEvents = bill.getDomainEvents();
        assertTrue(domainEvents.get(0) instanceof BillSettledEvent);
    }


}
