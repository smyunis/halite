package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.DomainEventHandler;
import com.smyunis.halite.domain.billing.Bill;

public class Caterer {
    private final CatererData data;

    public Caterer(CatererData data) {
        this.data = data;
    }

    public int getRecommendationMetric() {
        return data.getRecommendationMetric();
    }

    public void handleBillSettledEvent (Bill settledBill) {
        if (settledBill.getOutstandingAmount().amount() > 5000)
            data.setRecommendationMetric(data.getRecommendationMetric() + 5);
    }


//    public class BillSettledEventHandler implements DomainEventHandler<BillSettledEvent> {
//        @Override
//        public void handleEvent(BillSettledEvent billSettledEvent) {
//            var settledBill = billSettledEvent.getBill();
//            if (settledBill.getOutstandingAmount().amount() > 5000)
//                data.setRecommendationMetric(data.getRecommendationMetric() + 5);
//        }
//    }
}
