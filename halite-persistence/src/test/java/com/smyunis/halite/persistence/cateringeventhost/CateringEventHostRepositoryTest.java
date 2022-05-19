package com.smyunis.halite.persistence.cateringeventhost;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHost;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostData;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostRepository;
import com.smyunis.halite.domain.shared.PhoneNumber;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CateringEventHostRepositoryTest {
    private static CateringEventHostRepository cateringEventHostRepository;

    @BeforeAll
    static void dbSetup() {
        JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();
        cateringEventHostRepository = new CateringEventHostRepositoryImpl(jdbcTemplate);
    }

    @Test
    void canSaveThenGetCateringEventHost() {
        CateringEventHostId cateringEventHostId = new CateringEventHostId("Id-00001-TEST-HOST");
        CateringEventHostData cateringEventHostData = new CateringEventHostData()
                .setId(cateringEventHostId)
                .setName("Bob Burger")
                .setPhoneNumber(new PhoneNumber("+69124578963"));

        cateringEventHostRepository.save(new CateringEventHost(cateringEventHostData));
        var res = cateringEventHostRepository.get(cateringEventHostId);

        assertThrows(EntityNotFoundException.class,() -> {
            cateringEventHostRepository.get(new CateringEventHostId());
        });
        assertEquals(cateringEventHostData.getId(),res.getDataReadOnlyProxy().getId());
        assertEquals(cateringEventHostData.getName(),res.getDataReadOnlyProxy().getName());
        assertEquals(cateringEventHostData.getPhoneNumber(),res.getDataReadOnlyProxy().getPhoneNumber());
    }
}
