package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private BillId id;
    private BillStatus status = BillStatus.Pending;
    private CatererId payeeId;
    private CateringEventHostId payerId;
    private CateringEventId cateringEventId;
    private LocalDateTime dueDate;
    private LocalDateTime settledDateTime;
    private OutstandingAmount outstandingAmount;
    private String remark;
    private List<DomainEvent> domainEvents = new ArrayList<>();

    public void settle() {
        if (isDueForSettlement()) {
            throw new InvalidOperationException("");
        }
        status = BillStatus.Settled;
        settledDateTime = LocalDateTime.now();
        domainEvents.add(new BillSettledEvent(this));
    }

    public void cancel() {
        if (status == BillStatus.Settled)
            throw new InvalidOperationException("Bill already paid");
        status = BillStatus.Cancelled;
    }

    public BillStatus getStatus() {
        return status;
    }

    void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    void setOutstandingAmount(OutstandingAmount outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public void addRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    private boolean isDueForSettlement() {
        return status != BillStatus.Pending || dueDate.isBefore(LocalDateTime.now());
    }

    public List<DomainEvent> getDomainEvents() {
        return this.domainEvents;
    }

    public OutstandingAmount getOutstandingAmount() {
        return outstandingAmount;
    }
}
