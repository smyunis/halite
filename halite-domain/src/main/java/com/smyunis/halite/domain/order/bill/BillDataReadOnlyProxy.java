package com.smyunis.halite.domain.order.bill;

import com.smyunis.halite.domain.shared.MonetaryAmount;

import java.time.LocalDateTime;

public class BillDataReadOnlyProxy {
    private final BillData data;

    public BillDataReadOnlyProxy(BillData data) {
        this.data = data;
    }

    public BillId getId() {
        return data.getId();
    }

    public BillStatus getBillStatus() {
        return data.getBillStatus();
    }

    public MonetaryAmount getOutstandingAmount() {
        return data.getOutstandingAmount();
    }

    public LocalDateTime getDueDateTime() {
        return data.getDueDateTime();
    }

    public LocalDateTime getSettlementDateTime() {
        return data.getSettlementDateTime();
    }

    public String getRemark() {
        return data.getRemark();
    }
}
