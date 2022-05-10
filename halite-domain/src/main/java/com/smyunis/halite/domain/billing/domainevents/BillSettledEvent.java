package com.smyunis.halite.domain.billing.domainevents;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.billing.Bill;

public class BillSettledEvent extends DomainEvent {
    private Bill bill;

    public BillSettledEvent(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }
}
