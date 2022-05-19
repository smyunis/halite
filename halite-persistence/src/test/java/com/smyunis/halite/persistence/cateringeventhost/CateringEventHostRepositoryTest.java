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
    void t() {
        CateringEventHostId cateringEventHostId = new CateringEventHostId("Id-0000-1");
        CateringEventHostData cateringEventHostData = new CateringEventHostData()
                .setId(cateringEventHostId)
                .setName("bob")
                .setPhoneNumber(new PhoneNumber("+69124578963"));

        cateringEventHostRepository.save(new CateringEventHost(cateringEventHostData));
        var res = cateringEventHostRepository.get(cateringEventHostId);

        assertThrows(EntityNotFoundException.class,() -> {
            cateringEventHostRepository.get(new CateringEventHostId());
        });
        assertEquals("Id-0000-1",res.getDataReadOnlyProxy().getId().toString());
        assertEquals("bob",res.getDataReadOnlyProxy().getName());
        assertEquals("+69124578963",res.getDataReadOnlyProxy().getPhoneNumber().phoneNumber());
    }
}
