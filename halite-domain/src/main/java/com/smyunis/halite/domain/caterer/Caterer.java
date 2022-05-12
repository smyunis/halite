package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.DomainEventHandler;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;

public class Caterer {
    private final CatererData catererData;

    public Caterer(CatererData catererData) {
        this.catererData = catererData;
    }

    public int getRecommendationMetric() {
        return catererData.getRecommendationMetric();
    }

    public class BillSettledEventHandler implements DomainEventHandler<BillSettledEvent> {
        @Override
        public void handleEvent(BillSettledEvent billSettledEvent) {
            var settledBill = billSettledEvent.getBill();
            if (settledBill.getOutstandingAmount().amount() > 5000)
                catererData.setRecommendationMetric(catererData.getRecommendationMetric() + 5);
        }
    }
}
