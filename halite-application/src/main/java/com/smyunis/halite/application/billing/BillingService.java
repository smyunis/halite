package com.smyunis.halite.application.billing;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.BillRepository;

public class BillingService {
    private DomainEventDispatcher domainEventManager;
    private final BillRepository billRepository;
    public BillingService(DomainEventDispatcher domainEventManager, BillRepository billRepository) {
        this.domainEventManager = domainEventManager;
        this.billRepository = billRepository;
    }

    public void settleBill(BillId billId) {
        Bill bill = billRepository.get(billId);
        bill.settle();
        billRepository.save(bill);
        dispatchDomainEvents(bill);
    }

    public void requestBillCancellation(BillId billId) {
        Bill bill = billRepository.get(billId);
        bill.requestCancellation();
        billRepository.save(bill);
    }

    public void approveBillCancellation(BillId billId) {
        Bill bill = billRepository.get(billId);
        bill.approveCancellation();
        billRepository.save(bill);
    }

    private void dispatchDomainEvents(Bill bill) {
        domainEventManager.registerDomainEvents(bill.getDomainEvents());
        domainEventManager.publish();
    }
}
