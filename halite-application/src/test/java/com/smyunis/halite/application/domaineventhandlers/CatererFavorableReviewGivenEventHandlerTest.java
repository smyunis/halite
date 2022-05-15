package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.application.catererreview.ReviewService;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.catererreview.*;
import com.smyunis.halite.domain.catererreview.domainevents.FavorableReviewGivenEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CatererFavorableReviewGivenEventHandlerTest {
    private CatererRepository catererRepository;
    private ReviewService reviewService;
    private ReviewData reviewData;
    private CatererId catererId;

    @BeforeEach
    void setup() {
        catererId = new CatererId("XXx-YYY");
        var reviewId = new ReviewId();
        reviewData = new ReviewData()
                .setId(reviewId)
                .setReviewedCatererId(catererId)
                .setRating(new Rating(5));
        Review review = new Review(reviewData);

        DomainEventDispatcher dispatcher = new DomainEventDispatcher();
        ReviewRepository reviewRepo = mock(ReviewRepository.class);
        when(reviewRepo.get(reviewId)).thenReturn(review);
        reviewService = new ReviewService(dispatcher, reviewRepo);

        catererRepository = mock(CatererRepository.class);
        when(catererRepository.get(catererId)).thenReturn(new Caterer(new CatererData().setId(catererId)));
        var eventHandler = new CatererFavorableReviewGivenEventHandler(catererRepository);
        dispatcher.assignHandler(FavorableReviewGivenEvent.class, eventHandler);
    }

    @Test
    void canHandleDomainEvent() {
        reviewService.addReview(reviewData);

        verify(catererRepository).get(catererId);
        verify(catererRepository).save(any());

        assertEquals(2, catererRepository.get(catererId).getRecommendationMetric());
    }
}
