package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;

public class ReviewDataReadOnlyProxy {
    private final ReviewData data;

    public ReviewDataReadOnlyProxy(ReviewData data) {
        this.data = data;
    }

    public ReviewId getId() {
        return data.getId();
    }

    public CateringEventHostId getReviewerId() {
        return data.getReviewerId();
    }

    public CatererId getReviewedCatererId() {
        return data.getReviewedCatererId();
    }

    public String getContent() {
        return data.getContent();
    }

    public Rating getRating() {
        return data.getRating();
    }
}
