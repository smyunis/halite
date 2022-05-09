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

    public Rating getRating() {
        return rating;
    }

    void setRating(Rating rating) {
        this.rating = rating;
    }

    public ReviewId getId() {
        return id;
    }

    void setId(ReviewId id) {
        this.id = id;
    }

    public CateringEventHostId getReviewerId() {
        return reviewerId;
    }

    void setReviewerId(CateringEventHostId reviewerId) {
        this.reviewerId = reviewerId;
    }

    public CatererId getReviewedCatererId() {
        return reviewedCatererId;
    }

    void setReviewedCatererId(CatererId reviewedCatererId) {
        this.reviewedCatererId = reviewedCatererId;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }
}
