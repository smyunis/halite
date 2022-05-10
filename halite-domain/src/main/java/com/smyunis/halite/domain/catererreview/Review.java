package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;

public class Review {
    private ReviewId id;
    private CateringEventHostId reviewerId;
    private CatererId reviewedCatererId;
    private String title;
    private String content;
    private Rating rating;

    void setRating(Rating rating) {
        this.rating = rating;
    }


}
