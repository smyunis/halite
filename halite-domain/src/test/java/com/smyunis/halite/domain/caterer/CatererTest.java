package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillData;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.OutstandingAmount;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CatererTest {
    private Caterer caterer;

    @BeforeEach
    void setup() {
        caterer = new Caterer();
    }


    @Test
    void canHandleBillSettledEvent() {
        BillData billData = new BillData();
        billData.setOutstandingAmount(new OutstandingAmount(6000))
                .setPayeeId(caterer.getId());
        Bill bill = new Bill(billData);

        BillSettledEvent billSettledEvent = new BillSettledEvent(bill);
        caterer.new DomainEventHandler()
                .handleBillSettledEvent(billSettledEvent);

        assertEquals(5,caterer.getRecommendationMetric());

    }

}
