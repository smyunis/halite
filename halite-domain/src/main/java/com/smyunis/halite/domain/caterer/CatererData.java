package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.shared.PhoneNumber;

public class CatererData {
    private CatererId id = new CatererId();
    private String name;
    private PhoneNumber phoneNumber;
    private int recommendationMetric;

    public CatererId getId() {
        return id;
    }

    public CatererData setId(CatererId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CatererData setName(String name) {
        this.name = name;
        return this;
    }

    public int getRecommendationMetric() {
        return recommendationMetric;
    }

    public CatererData setRecommendationMetric(int recommendationMetric) {
        this.recommendationMetric = recommendationMetric;
        return this;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public CatererData setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
