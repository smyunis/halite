package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillData;
import com.smyunis.halite.domain.billing.MonetaryAmount;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatererTest {
    private Caterer caterer;
    private CatererData catererData;

    @BeforeEach
    void setup() {
        catererData = new CatererData();
        caterer = new Caterer(catererData);
    }

    @Test
    void canHandleBillSettledEvent() {
        BillData billData = new BillData();

        catererData.setId(new CatererId());
        Caterer handlerCaterer = new Caterer(catererData);

        billData.setOutstandingAmount(new MonetaryAmount(6000))
                .setPayeeId(catererData.getId());

        Bill bill = new Bill(billData);

        handlerCaterer.handleBillSettledEvent(bill);

        assertEquals(5,handlerCaterer.getRecommendationMetric());
    }

    @Test
    void catererRecommendationMetricWillBeDeductedIfOrderIsRejected() {
        var orderData = new OrderData();
        orderData.setStatus(OrderStatus.REJECTED);
        var rejectedOrder = new Order(orderData);
        int initialRecMetric = caterer.getRecommendationMetric();

        caterer.handleOrderRejectedEvent(rejectedOrder);

        assertTrue(initialRecMetric > caterer.getRecommendationMetric());
        assertEquals(-1,caterer.getRecommendationMetric());
    }

}
