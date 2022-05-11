package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.DomainEventHandler;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;

public class Caterer {
    private CatererId id = new CatererId();
    private String name;
    private int recommendationMetric;

    public CatererId getId() {
        return id;
    }

    public int getRecommendationMetric() {
        return recommendationMetric;
    }

    public class BillSettledEventHandler implements DomainEventHandler<BillSettledEvent> {
        @Override
        public void handleEvent(BillSettledEvent billSettledEvent) {
            var settledBill = billSettledEvent.getBill();
            if (settledBill.getOutstandingAmount().amount() > 5000)
                recommendationMetric += 5;
        }
    }
}
