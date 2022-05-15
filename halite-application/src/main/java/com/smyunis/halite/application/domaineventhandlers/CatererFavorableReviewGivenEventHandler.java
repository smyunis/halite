package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.catererreview.Review;
import com.smyunis.halite.domain.catererreview.domainevents.FavorableReviewGivenEvent;

public class CatererFavorableReviewGivenEventHandler implements
        DomainEventHandler<FavorableReviewGivenEvent> {
    private final CatererRepository catererRepository;

    public CatererFavorableReviewGivenEventHandler(CatererRepository catererRepository) {
        this.catererRepository = catererRepository;
    }

    @Override
    public void handleEvent(FavorableReviewGivenEvent domainEvent) {
        Review review = domainEvent.getReview();
        CatererId reviewedCatererId = review.getReviewedCatererId();
        Caterer reviewedCaterer = catererRepository.get(reviewedCatererId);
        reviewedCaterer.handleFavorableReviewGivenEvent();
        catererRepository.save(reviewedCaterer);
    }
}
