package com.smyunis.halite.domain.order.bill;

import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.shared.MonetaryAmount;

import java.time.LocalDateTime;

public class Bill {
    private final BillData data;

    public Bill(BillData billData) {
        this.data = billData;
    }

    public void settle() {
        if (billNotValidForSettlement()) {
            throw new InvalidOperationException("Bill not pending settlement or its due date passed");
        }
        data.setBillStatus(BillStatus.SETTLED);
        data.setSettlementDateTime(LocalDateTime.now());
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

    public BillStatus getBillStatus() {
        return data.getBillStatus();
    }

    public MonetaryAmount getOutstandingAmount() {
        return data.getOutstandingAmount();
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

    private boolean billNotValidForSettlement() {
        return data.getBillStatus() != BillStatus.PENDING_SETTLEMENT || data.getDueDateTime().isBefore(LocalDateTime.now());
    }
}
