package com.smyunis.halite.persistence.caterer;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.shared.PhoneNumber;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CatererRepositoryTest {

    private static CatererRepositoryImpl catererRepository;

    @BeforeAll
    static void dbSetup() {
        JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();
        catererRepository = new CatererRepositoryImpl(jdbcTemplate);
    }

    @Test
    void catererRepoTest() {
        CatererId catererId = new CatererId("Id-00001-TEST-CATER");
        Caterer caterer = new Caterer(new CatererData()
                .setName("Gordon Ramsey")
                .setPhoneNumber(new PhoneNumber("+441962359874"))
                .setRecommendationMetric(200)
                .setId(catererId));

        catererRepository.save(caterer);
        Caterer fetchedCaterer = catererRepository.get(catererId);
        var fetchedData = fetchedCaterer.getDataReadOnlyProxy();

        assertThrows(EntityNotFoundException.class,() -> {
            catererRepository.get(new CatererId());
        });
        assertEquals(200, fetchedData.getRecommendationMetric());
        assertEquals("Gordon Ramsey", fetchedData.getName());
        assertEquals(new PhoneNumber("+441962359874"), fetchedData.getPhoneNumber());
    }
}
