package com.smyunis.halite.application.billing;

import com.smyunis.halite.domain.billing.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillingServiceTest {
    private BillRepository billRepository;
    private BillingService billingService;
    private BillId billId;

    @BeforeEach
    void setup() {
        billId = new BillId();
        billRepository = mock(BillRepository.class);
        billingService = new BillingService(billRepository);
    }

    @Test
    void cateringEventHostCanSettleBill() {
        var billData = new BillData()
                .setId(billId);
        when(billRepository.get(billId)).thenReturn(new Bill(billData));

        billingService.settleBill(billId);

        assertEquals(BillStatus.Settled, billRepository.get(billId).getBillStatus());
    }

    @Test
    void cateringEventHostCanRequestCancellationOfABillPendingSettlement() {
        var billData = new BillData()
                .setId(billId)
                .setBillStatus(BillStatus.PendingSettlement);
        when(billRepository.get(billId)).thenReturn(new Bill(billData));

        billingService.requestBillCancellation(billId);

        assertEquals(BillStatus.PendingCancellation,billRepository.get(billId).getBillStatus());
    }


}
