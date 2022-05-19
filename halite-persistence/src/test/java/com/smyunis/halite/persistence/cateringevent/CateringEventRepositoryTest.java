package com.smyunis.halite.persistence.cateringevent;

import com.smyunis.halite.domain.cateringevent.*;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CateringEventRepositoryTest {
    private static final JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();
    private final CateringEventRepository repository = new CateringEventRepositoryImpl(jdbcTemplate);

    @Test
    void canSaveThenGetCateringEvent() {
        CateringEventId cateringEventId = new CateringEventId();
        var hostId = new CateringEventHostId();

        var now = LocalDateTime.now();

        CateringEventData cateringEventData = new CateringEventData()
                .setId(cateringEventId)
                .setCateringEventHostId(hostId)
                .setEventStartTime(now)
                .setEventEndTime(now.plusDays(6))
                .setDescription("Fancy Wedding")
                .setVenue(new Venue("Mordor"))
                .setExpectedNumberOfAttendees(new NumberOfAttendees(500));

        repository.save(new CateringEvent(cateringEventData));
        var event = repository.get(cateringEventId);

        assertEquals(cateringEventData.getId(), event.getId());
        assertEquals(cateringEventData.getCateringEventHostId(), event.getDataReadOnlyProxy().getCateringEventHostId());
        assertEquals(cateringEventData.getStatus(), event.getDataReadOnlyProxy().getStatus());
        assertEquals(cateringEventData.getVenue(), event.getDataReadOnlyProxy().getVenue());
        assertEquals(cateringEventData.getEventStartTime().toEpochSecond(ZoneOffset.UTC), event.getDataReadOnlyProxy().getEventStartTime().toEpochSecond(ZoneOffset.UTC));
        assertEquals(cateringEventData.getEventEndTime().toEpochSecond(ZoneOffset.UTC), event.getDataReadOnlyProxy().getEventEndTime().toEpochSecond(ZoneOffset.UTC));
        assertEquals(cateringEventData.getDescription(), event.getDataReadOnlyProxy().getDescription());
        assertEquals(cateringEventData.getExpectedNumberOfAttendees(), event.getDataReadOnlyProxy().getExpectedNumberOfAttendees());

    }
}
