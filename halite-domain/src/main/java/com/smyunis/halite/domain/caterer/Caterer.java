package com.smyunis.halite.domain.caterer;

public class Caterer {
    private CatererId id = new CatererId();
    private String name;
    private int recommendationMetric;

    public CatererId getId() {
        return id;
    }

    void setId(CatererId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getRecommendationMetric() {
        return recommendationMetric;
    }
}
