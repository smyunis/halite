package com.smyunis.halite.application.billing;

import com.smyunis.halite.domain.DomainEventsDispatcher;
import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.BillRepository;

public class BillingService {
    private DomainEventsDispatcher domainEventManager;
    private final BillRepository billRepository;
    public BillingService(DomainEventsDispatcher domainEventManager, BillRepository billRepository) {
        this.domainEventManager = domainEventManager;
        this.billRepository = billRepository;
    }

    public void settleBill(BillId billId) {
        Bill bill = billRepository.get(billId);
        bill.settle();
        publishDomainEvents(bill);
        billRepository.save(bill);
    }

    public void requestBillCancellation(BillId billId) {
        Bill bill = billRepository.get(billId);
        bill.requestCancellation();
        billRepository.save(bill);
    }

    private void publishDomainEvents(Bill bill) {
        domainEventManager.registerDomainEvents(bill.getDomainEvents());
        domainEventManager.publish();
    }
}
