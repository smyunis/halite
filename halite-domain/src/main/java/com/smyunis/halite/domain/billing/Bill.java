package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bill {
    private final BillData data;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Bill(BillData billData) {
        this.data = billData;
    }

    public void settle() {
        if (billNotValidForSettlement()) {
            throw new InvalidOperationException("Bill not pending settlement or its due date passed");
        }
        data.setBillStatus(BillStatus.SETTLED);
        data.setSettlementDateTime(LocalDateTime.now());
        domainEvents.add(new BillSettledEvent(this));
    }

    public void requestCancellation() {
        if(data.getBillStatus() != BillStatus.PENDING_SETTLEMENT)
            throw new InvalidOperationException("Bill should be pending settlement for it to be cancelled");
        data.setBillStatus(BillStatus.PENDING_CANCELLATION);
    }

    public void approveCancellation() {
        if (data.getBillStatus() != BillStatus.PENDING_CANCELLATION)
            throw new InvalidOperationException("Bill is not pending cancellation");
        data.setBillStatus(BillStatus.CANCELLED);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }

    public BillStatus getBillStatus() {
        return data.getBillStatus();
    }

    private boolean billNotValidForSettlement() {
        return data.getBillStatus() != BillStatus.PENDING_SETTLEMENT || data.getDueDateTime().isBefore(LocalDateTime.now());
    }

    public MonetaryAmount getOutstandingAmount() {
        return data.getOutstandingAmount();
    }

    public CatererId getPayeeId() {
        return data.getPayeeId();
    }

    public void incrementOutstandingAmount(MonetaryAmount addend) {
        MonetaryAmount initialAmount = data.getOutstandingAmount();
        var newOutstandingAmount = initialAmount.amount() + addend.amount();
        data.setOutstandingAmount(new MonetaryAmount(newOutstandingAmount));
    }

    public void decrementOutstandingAmount(MonetaryAmount subtrahend) {
        var initialAmount = data.getOutstandingAmount();
        var newOutstandingAmount = initialAmount.amount() - subtrahend.amount();
        data.setOutstandingAmount(new MonetaryAmount(newOutstandingAmount));
    }
}
