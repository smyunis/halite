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
    private final BillId id;
    private BillStatus status = BillStatus.Pending;
    private CatererId payeeId;
    private CateringEventHostId payerId;
    private CateringEventId cateringEventId;
    private OutstandingAmount outstandingAmount;
    private LocalDateTime dueDate = LocalDateTime.now().plusMonths(1);
    private LocalDateTime settlementDateTime;
    private String remark;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Bill(BillId id,
         CatererId payeeId,
         CateringEventHostId payerId,
         CateringEventId cateringEventId,
         OutstandingAmount outstandingAmount) {
        this.id = id;
        this.payeeId = payeeId;
        this.payerId = payerId;
        this.cateringEventId = cateringEventId;
        this.outstandingAmount = outstandingAmount;
    }


    public void settle() {
        if (billNotDueForSettlement()) {
            throw new InvalidOperationException("");
        }
        status = BillStatus.Settled;
        settlementDateTime = LocalDateTime.now();
        domainEvents.add(new BillSettledEvent(this));
    }

    public void cancel() {
        if (status == BillStatus.Settled)
            throw new InvalidOperationException("Bill already paid");
        status = BillStatus.Cancelled;
    }

    public BillId getId() {
        return id;
    }

    public CatererId getPayeeId() {
        return payeeId;
    }

    public CateringEventHostId getPayerId() {
        return payerId;
    }

    public CateringEventId getCateringEventId() {
        return cateringEventId;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void addRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public List<DomainEvent> getDomainEvents() {
        return this.domainEvents;
    }

    public OutstandingAmount getOutstandingAmount() {
        return outstandingAmount;
    }

    private boolean billNotDueForSettlement() {
        return status != BillStatus.Pending || dueDate.isBefore(LocalDateTime.now());
    }
}
