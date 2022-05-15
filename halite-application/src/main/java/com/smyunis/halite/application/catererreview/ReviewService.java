package com.smyunis.halite.application.catererreview;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.catererreview.Review;
import com.smyunis.halite.domain.catererreview.ReviewData;
import com.smyunis.halite.domain.catererreview.ReviewDomainService;
import com.smyunis.halite.domain.catererreview.ReviewRepository;

public class ReviewService {
    private DomainEventDispatcher eventDispatcher;
    private ReviewRepository reviewRepository;

    public ReviewService(DomainEventDispatcher eventDispatcher, ReviewRepository reviewRepository) {
        this.eventDispatcher = eventDispatcher;
        this.reviewRepository = reviewRepository;
    }

    public void addReview(ReviewData reviewPayload) {
        ReviewDomainService reviewDomainService = new ReviewDomainService();
        Review review = createReview(reviewPayload, reviewDomainService);
        dispatchDomainEvents(review);
    }

    private Review createReview(ReviewData reviewPayload, ReviewDomainService reviewDomainService) {
        Review review = reviewDomainService.createReview(reviewPayload);
        reviewRepository.save(review);
        return review;
    }

    private void dispatchDomainEvents(Review review) {
        eventDispatcher.registerDomainEvents(review.getDomainEvents());
        eventDispatcher.publish();
    }
}
