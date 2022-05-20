package com.smyunis.halite.persistence.catererreview;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.catererreview.*;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewRepositoryTest {
    private static final JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();

    private final ReviewRepository repository = new ReviewRepositoryImpl(jdbcTemplate);

    @Test
    void canSaveThenGetReview() {
        ReviewId reviewId = new ReviewId();
        CateringEventHostId reviewer = new CateringEventHostId("Id-00001-TEST-HOST");
        CatererId reviewed = new CatererId("Id-00001-TEST-CATER");
        var reviewData = new ReviewData()
                .setId(reviewId)
                .setReviewerId(reviewer)
                .setReviewedCatererId(reviewed)
                .setRating(new Rating(4))
                .setContent("Good food. Nice Service. Waiter looked at me funny once");

        repository.save(new Review(reviewData));
        var fetchedReview = repository.get(reviewId);

        assertEquals(reviewData.getId(),fetchedReview.getDataReadOnlyProxy().getId());
        assertEquals(reviewData.getReviewerId(),fetchedReview.getDataReadOnlyProxy().getReviewerId());
        assertEquals(reviewData.getReviewedCatererId(),fetchedReview.getDataReadOnlyProxy().getReviewedCatererId());
        assertEquals(reviewData.getRating(),fetchedReview.getDataReadOnlyProxy().getRating());
        assertEquals(reviewData.getContent(),fetchedReview.getDataReadOnlyProxy().getContent());
    }
}