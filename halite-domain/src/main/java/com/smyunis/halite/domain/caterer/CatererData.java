package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.shared.PhoneNumber;

import java.net.URL;

public class CatererData {
    private CatererId id = new CatererId();
    private String name;
    private PhoneNumber phoneNumber;
    private String personalDescription;
    private URL catererImage;
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

    public String getPersonalDescription() {
        return personalDescription;
    }

    public CatererData setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
        return this;
    }

    public URL getCatererImage() {
        return catererImage;
    }

    public CatererData setCatererImage(URL catererImage) {
        this.catererImage = catererImage;
        return this;
    }
}
