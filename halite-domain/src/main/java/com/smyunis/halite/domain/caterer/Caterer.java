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


}
