package com.smyunis.halite.application.billing;

import com.smyunis.halite.domain.DomainEventManager;
import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.BillRepository;

public class BillingService {
    private DomainEventManager domainEventManager;
    private final BillRepository billRepository;
    public BillingService(DomainEventManager domainEventManager, BillRepository billRepository) {
        this.domainEventManager = domainEventManager;
        this.billRepository = billRepository;
    }

    public void settleBill(BillId billId) {
        Bill bill = billRepository.get(billId);
        bill.settle();
        domainEventManager.registerDomainEvents(bill.getDomainEvents());

        billRepository.save(bill);
    }

    public void requestBillCancellation(BillId billId) {
        Bill bill = billRepository.get(billId);
        bill.requestCancellation();
        billRepository.save(bill);
    }
}
