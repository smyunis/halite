package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.shared.PhoneNumber;

public class CatererDataReadOnlyProxy {
    private final CatererData data;

    public CatererDataReadOnlyProxy(CatererData data) {
        this.data = data;
    }

    public CatererId getId() {
        return data.getId();
    }

    public String getName() {
        return data.getName();
    }

    public PhoneNumber getPhoneNumber() {
        return data.getPhoneNumber();
    }

    public int getRecommendationMetric() {
        return data.getRecommendationMetric();
    }
}
