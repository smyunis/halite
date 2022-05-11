package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private final BillData billData;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Bill(BillData billData) {
        this.billData = billData;
    }

    public void settle() {
        if (billNotValidForSettlement()) {
            throw new InvalidOperationException("Bill not pending settlement or its due date passed");
        }
        billData.setBillStatus(BillStatus.Settled);
        billData.setSettlementDateTime(LocalDateTime.now());
        domainEvents.add(new BillSettledEvent(this));
    }

    public void requestCancellation() {
        if(billData.getBillStatus() != BillStatus.PendingSettlement)
            throw new InvalidOperationException("Bill should be pending settlement for it to be cancelled");
        billData.setBillStatus(BillStatus.PendingCancellation);
    }

    public void approveCancellation() {
        if (billData.getBillStatus() != BillStatus.PendingCancellation)
            throw new InvalidOperationException("Bill is not pending cancellation");
        billData.setBillStatus(BillStatus.Cancelled);
    }

    public List<DomainEvent> getDomainEvents() {
        return this.domainEvents;
    }

    public BillStatus getBillStatus() {
        return billData.getBillStatus();
    }

    private boolean billNotValidForSettlement() {
        return billData.getBillStatus() != BillStatus.PendingSettlement || billData.getDueDateTime().isBefore(LocalDateTime.now());
    }

    public OutstandingAmount getOutstandingAmount() {
        return billData.getOutstandingAmount();
    }

}
