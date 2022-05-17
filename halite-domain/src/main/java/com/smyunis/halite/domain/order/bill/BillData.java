package com.smyunis.halite.domain.order.bill;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.shared.MonetaryAmount;

import java.time.LocalDateTime;

public class BillData {
    private BillId id = new BillId();
    private BillStatus billStatus = BillStatus.PENDING_SETTLEMENT;
    private MonetaryAmount outstandingAmount = new MonetaryAmount(0.0);
    private LocalDateTime dueDateTime = LocalDateTime.now().plusMonths(1);
    private LocalDateTime settlementDateTime;
    private String remark;

    public BillId getId() {
        return id;
    }

    public BillData setId(BillId id) {
        this.id = id;
        return this;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public BillData setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
        return this;
    }

    public MonetaryAmount getOutstandingAmount() {
        return outstandingAmount;
    }

    public BillData setOutstandingAmount(MonetaryAmount outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
        return this;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public BillData setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
        return this;
    }

    public LocalDateTime getSettlementDateTime() {
        return settlementDateTime;
    }

    public BillData setSettlementDateTime(LocalDateTime settlementDateTime) {
        assertSettlementDateTimeIsNotPastDueData(settlementDateTime);
        this.settlementDateTime = settlementDateTime;
        return this;
    }

    private void assertSettlementDateTimeIsNotPastDueData(LocalDateTime settlementDateTime) {
        if (settlementDateTime.isAfter(dueDateTime))
            throw new InvalidValueException("Settlement datetime is after due datetime");
    }

    public String getRemark() {
        return remark;
    }

    public BillData setRemark(String remark) {
        this.remark = remark;
        return this;
    }

}
