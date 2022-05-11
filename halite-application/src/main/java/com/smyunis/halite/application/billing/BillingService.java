package com.smyunis.halite.application.billing;

import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.BillRepository;

public class BillingService {
    private final BillRepository billRepository;
    public BillingService(BillRepository billRepository) {

        this.billRepository = billRepository;
    }
    public void settleBill(BillId billId) {
        Bill bill = billRepository.get(billId);
        bill.settle();
        billRepository.save(bill);
    }

    public void requestBillCancellation(BillId billId) {
        Bill bill = billRepository.get(billId);
        bill.requestCancellation();
        billRepository.save(bill);
    }
}
