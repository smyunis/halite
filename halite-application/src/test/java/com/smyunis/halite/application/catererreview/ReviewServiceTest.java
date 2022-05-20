package com.smyunis.halite.application.catererreview;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.catererreview.*;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    private ReviewService reviewService;
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setup() {
        reviewRepository = mock(ReviewRepository.class);
        DomainEventDispatcher eventDispatcher = mock(DomainEventDispatcher.class);
        reviewService = new ReviewService(eventDispatcher,reviewRepository);
    }

    @Test
    void addReview() {
        ReviewId reviewId = new ReviewId();
        ReviewData reviewPayload = new ReviewData();
        reviewPayload.setId(reviewId);
        reviewPayload.setRating(new Rating(5));
        reviewPayload.setReviewerId(new CateringEventHostId());
        reviewPayload.setContent("Very good food.");
        reviewPayload.setReviewedCatererId(new CatererId());

        reviewService.addReview(reviewPayload);

        verify(reviewRepository).save(any(Review.class));
    }
}
