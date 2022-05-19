package com.smyunis.halite.persistence.caterer;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.shared.PhoneNumber;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatererRepositoryTest {

    private static CatererRepositoryImpl catererRepository;

    @BeforeAll
    static void dbSetup() {
        JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();
        catererRepository = new CatererRepositoryImpl(jdbcTemplate);
    }

    @Test
    void catererRepoTest() {
        CatererId catererId = new CatererId("Id-00001-TST");
        Caterer caterer = new Caterer(new CatererData()
                .setName("T Cat")
                .setPhoneNumber(new PhoneNumber("+251978452396"))
                .setId(catererId));

        catererRepository.save(caterer);
        Caterer fetchedCaterer = catererRepository.get(catererId);
        var fetchedData = fetchedCaterer.getDataReadOnlyProxy();

        assertEquals(0, fetchedData.getRecommendationMetric());
        assertEquals("T Cat", fetchedData.getName());
        assertEquals(new PhoneNumber("+251978452396"), fetchedData.getPhoneNumber());
    }
}
