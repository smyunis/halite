package com.smyunis.halite.persistence.cateringevent;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.cateringevent.*;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CateringEventRepositoryImpl implements CateringEventRepository {
    private final JdbcTemplate jdbcTemplate;

    public CateringEventRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CateringEvent get(CateringEventId id) {
        try {
            return tryGetCateringEvent(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(id.toString(), exception);
        }
    }

    private CateringEvent tryGetCateringEvent(CateringEventId id) {
        String sql = "SELECT * FROM catering_event WHERE catering_event_id = ?";
        var data = jdbcTemplate.queryForObject(sql, this::mapCateringEvent, id.toString());
        return new CateringEvent(data);
    }

    private CateringEventData mapCateringEvent(ResultSet resultSet, int row) throws SQLException {
        return new CateringEventData()
                .setId(new CateringEventId(resultSet.getString("catering_event_id")))
                .setCateringEventHostId(new CateringEventHostId(resultSet.getString("catering_event_host_id")))
                .setStatus(CateringEventStatus.valueOf(resultSet.getString("status")))
                .setVenue(new Venue(resultSet.getString("venue")))
                .setDescription(resultSet.getString("description"))
                .setExpectedNumberOfAttendees(new NumberOfAttendees(resultSet.getInt("expected_number_of_attendees")))
                .setEventStartTime(resultSet.getTimestamp("event_start_time").toLocalDateTime())
                .setEventEndTime(resultSet.getTimestamp("event_end_time").toLocalDateTime());
    }

    @Override
    public void save(CateringEvent entity) {
        var data = entity.getDataReadOnlyProxy();
        String upsertSql = """
                INSERT INTO catering_event
                VALUES (?,?,?,?,?,?,?,?)
                ON CONFLICT (catering_event_id) DO UPDATE
                SET catering_event_host_id = excluded.catering_event_host_id,
                    status = excluded.status,
                    venue = excluded.venue,
                    description = excluded.description,
                    expected_number_of_attendees = excluded.expected_number_of_attendees,
                    event_start_time = excluded.event_start_time,
                    event_end_time = excluded.event_end_time;
                """;
        jdbcTemplate.update(upsertSql,
                data.getId().toString(),
                data.getCateringEventHostId().toString(),
                data.getStatus().toString(),
                data.getVenue().address(),
                data.getDescription(),
                data.getExpectedNumberOfAttendees().numberOfAttendees(),
                data.getEventStartTime(),
                data.getEventEndTime()
        );
    }
}
