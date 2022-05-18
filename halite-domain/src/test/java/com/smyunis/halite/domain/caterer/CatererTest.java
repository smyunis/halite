package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.catererreview.Rating;
import com.smyunis.halite.domain.catererreview.ReviewData;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.OrderStatus;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CatererTest {
    private Caterer caterer;
    private CatererData catererData;

    @BeforeEach
    void setup() {
        catererData = new CatererData();
        caterer = new Caterer(catererData);
    }

    @Test
    void canHandleBillSettledEvent() {
        Order order = new Order(new OrderData());
        order.incrementBillOutstandingAmount(new MonetaryAmount(23000));

        caterer.handleBillSettledEvent(order);

        assertEquals(5, caterer.getRecommendationMetric());
    }

    @Test
    void catererRecommendationMetricWillBeDeductedIfOrderIsRejected() {
        var orderData = new OrderData();
        orderData.setStatus(OrderStatus.REJECTED);

        int initialRecMetric = caterer.getRecommendationMetric();

        caterer.handleOrderRejectedEvent();

        assertTrue(initialRecMetric > caterer.getRecommendationMetric());
        assertEquals(-1, caterer.getRecommendationMetric());
    }

    @Test
    void catererRecommendationMetricWillBeIncreasedForAFavorableReview() {
        var reviewData = new ReviewData();
        reviewData.setRating(new Rating(5));

        int initialRecMetric = caterer.getRecommendationMetric();

        caterer.handleFavorableReviewGivenEvent();

        assertTrue(initialRecMetric < caterer.getRecommendationMetric());
        assertEquals(2, caterer.getRecommendationMetric());

    }

    @Test
    void checkCloneIsADeepCopyOfCatererData() {
        Caterer naruto = new Caterer(new CatererData().setRecommendationMetric(10));
        CatererData bunshinNarutoData = naruto.getCopyOfData();
        bunshinNarutoData.setRecommendationMetric(20);
        Caterer bunshinNaruto = new Caterer(bunshinNarutoData);

        assertNotEquals(naruto.getRecommendationMetric(),bunshinNaruto.getRecommendationMetric());
    }

}
