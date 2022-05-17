package com.smyunis.halite.application.catererreview;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.catererreview.Review;
import com.smyunis.halite.domain.catererreview.ReviewData;
import com.smyunis.halite.domain.catererreview.ReviewRepository;

public class ReviewService {
    private final DomainEventDispatcher eventDispatcher;
    private final ReviewRepository reviewRepository;

    public ReviewService(DomainEventDispatcher eventDispatcher, ReviewRepository reviewRepository) {
        this.eventDispatcher = eventDispatcher;
        this.reviewRepository = reviewRepository;
    }

    public void addReview(ReviewData reviewPayload) {
        Review review = new Review(reviewPayload);
        review.asNewReview();
        saveThenDispatchDomainEvents(review);
    }

    private void saveThenDispatchDomainEvents(Review review) {
        reviewRepository.save(review);
        dispatchDomainEvents(review);
    }

    private void dispatchDomainEvents(Review review) {
        eventDispatcher.registerDomainEvents(review.getDomainEvents());
        eventDispatcher.publish();
    }
}
