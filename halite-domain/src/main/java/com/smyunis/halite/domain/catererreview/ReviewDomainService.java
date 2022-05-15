package com.smyunis.halite.domain.catererreview;

public class ReviewDomainService {
    public Review createReview(ReviewData reviewData) {
        Review review = new Review(reviewData);
        review.asNewReview();
        return review;
    }
}
