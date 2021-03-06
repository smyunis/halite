package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.order.Order;

public class Caterer {
    private final CatererData data;

    public Caterer(CatererData data) {
        this.data = data;
    }

    public int getRecommendationMetric() {
        return data.getRecommendationMetric();
    }

    public void handleBillSettledEvent(Order orderWithSettledBill) {
        if (orderWithSettledBill.getBillOutstandingAmount().amount() > 5000)
            data.setRecommendationMetric(data.getRecommendationMetric() + 5);
    }

    public void handleOrderRejectedEvent() {
        data.setRecommendationMetric(data.getRecommendationMetric() - 1);
    }

    public void handleFavorableReviewGivenEvent() {
        data.setRecommendationMetric(data.getRecommendationMetric() + 2);
    }

    public CatererDataReadOnlyProxy getDataReadOnlyProxy() {
        return new CatererDataReadOnlyProxy(data);
    }
}
