package com.smyunis.halite.domain.catererreview;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;

public class ReviewData {
    private ReviewId id = new ReviewId();
    private CateringEventHostId reviewerId;
    private CatererId reviewedCatererId;
    private String title;
    private String content;
    private Rating rating;

    public ReviewId getId() {
        return id;
    }

    public ReviewData setId(ReviewId id) {
        this.id = id;
        return this;
    }

    public CateringEventHostId getReviewerId() {
        return reviewerId;
    }

    public ReviewData setReviewerId(CateringEventHostId reviewerId) {
        this.reviewerId = reviewerId;
        return this;
    }

    public CatererId getReviewedCatererId() {
        return reviewedCatererId;
    }

    public ReviewData setReviewedCatererId(CatererId reviewedCatererId) {
        this.reviewedCatererId = reviewedCatererId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ReviewData setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ReviewData setContent(String content) {
        this.content = content;
        return this;
    }

    public Rating getRating() {
        return rating;
    }

    public ReviewData setRating(Rating rating) {
        this.rating = rating;
        return this;
    }
}
