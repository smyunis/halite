package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;

import java.time.LocalDateTime;

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


    public void settle() {
        var now = LocalDateTime.now();
        if (dueDate.isBefore(now)) {
            throw new InvalidOperationException("Due for for bill has passed");
        }
        status = BillStatus.Paid;
        settledDateTime = now;
    }

    public BillStatus getStatus() {
        return status;
    }

    void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setOutstandingAmount(OutstandingAmount outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }
}
