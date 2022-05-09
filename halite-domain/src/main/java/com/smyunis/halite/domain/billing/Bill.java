package com.smyunis.halite.domain.billing;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;

import java.time.LocalDateTime;

public class Bill {
    private BillId id;
    private BillStatus billStatus = BillStatus.Pending;
    private CatererId payeeId;
    private CateringEventHostId payerId;
    private CateringEventId cateringEventId;
    private LocalDateTime dueDate;
    private OutstandingAmount outstandingAmount;
    private String remark;

    public void setOutstandingAmount(OutstandingAmount outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public OutstandingAmount getOutstandingAmount() {
        return outstandingAmount;
    }

    public String getRemark() {
        return remark;
    }

    void setRemark(String remark) {
        this.remark = remark;
    }
}
