package com.smyunis.halite.application.billing;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.billing.*;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillingServiceTest {
    private BillRepository billRepository;
    private BillingService billingService;
    private BillId billId;
    private BillData billData;

    @BeforeEach
    void setup() {
        billId = new BillId();
        billData = new BillData();
        billRepository = mock(BillRepository.class);
        when(billRepository.get(billId)).thenReturn(new Bill(billData));
        billingService = new BillingService(new DomainEventDispatcher(), billRepository);
    }

    @Test
    void cateringEventHostCanSettleBill() {
        var billData = new BillData()
                .setId(billId);
        when(billRepository.get(billId)).thenReturn(new Bill(billData));

        billingService.settleBill(billId);

        Bill settledBill = billRepository.get(billId);
        assertEquals(BillStatus.SETTLED, settledBill.getBillStatus());
        assertEquals(BillSettledEvent.class, settledBill.getDomainEvents().get(0).getClass());
    }

    @Test
    void cateringEventHostCanRequestCancellationOfABillPendingSettlement() {
        billData.setId(billId)
                .setBillStatus(BillStatus.PENDING_SETTLEMENT);

        billingService.requestBillCancellation(billId);

        assertEquals(BillStatus.PENDING_CANCELLATION, billRepository.get(billId).getBillStatus());
    }

    @Test
    void catererCanApproveCancellationOfABillPendingCancellation() {
        billData.setId(billId)
                .setBillStatus(BillStatus.PENDING_CANCELLATION);

        billingService.approveBillCancellation(billId);

        assertEquals(BillStatus.CANCELLED,billRepository.get(billId).getBillStatus());
    }




}
