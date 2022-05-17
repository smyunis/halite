package com.smyunis.halite.application.caterer;

import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillData;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatererBillSettledEventHandlerTest {
    @Test
    void canHandleBillSettledEvent() {
        CatererId catererId = new CatererId();
        CatererRepository mockedCatererRepository = mock(CatererRepository.class);
        when(mockedCatererRepository.get(catererId)).thenReturn(new Caterer(new CatererData().setId(catererId)));
        CatererBillSettledEventHandler handler = new CatererBillSettledEventHandler(mockedCatererRepository);
        handler.handleEvent(new BillSettledEvent(new Bill(new BillData()
                .setOutstandingAmount(new MonetaryAmount(6000))
                .setPayeeId(catererId))));

        assertEquals(5,mockedCatererRepository.get(catererId).getRecommendationMetric());
    }
}
