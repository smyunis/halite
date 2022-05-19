package com.smyunis.halite.persistence.cateringeventhost;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHost;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostData;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostRepository;
import com.smyunis.halite.domain.shared.PhoneNumber;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CateringEventHostRepositoryImpl implements CateringEventHostRepository {
    private final JdbcTemplate jdbcTemplate;

    public CateringEventHostRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public CateringEventHost get(CateringEventHostId id) {
        try {
            return tryGetCateringEventHost(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(id.toString(), exception);
        }
    }

    private CateringEventHost tryGetCateringEventHost(CateringEventHostId id) {
        String sql = "SELECT * FROM catering_event_host WHERE catering_event_host_id = ?";
        CateringEventHostData cateringEventHostData = jdbcTemplate.queryForObject(sql, this::mapCateringEventHostData, id.toString());
        return new CateringEventHost(cateringEventHostData);
    }

    @Override
    public void save(CateringEventHost domainEntity) {
        var readOnlyProxy = domainEntity.getDataReadOnlyProxy();
        String upsertSql = """
                INSERT INTO catering_event_host
                VALUES (?,?,?)
                ON CONFLICT (catering_event_host_id) DO UPDATE
                SET name = excluded.name,
                    phone_number = excluded.phone_number;    
                """;
        jdbcTemplate.update(upsertSql,
                readOnlyProxy.getId().toString(),
                readOnlyProxy.getName(),
                readOnlyProxy.getPhoneNumber().phoneNumber());
    }

    private CateringEventHostData mapCateringEventHostData(ResultSet resultSet, int row) throws SQLException {
        return new CateringEventHostData()
                .setId(new CateringEventHostId(resultSet.getString("catering_event_host_id")))
                .setName(resultSet.getString("name"))
                .setPhoneNumber(new PhoneNumber(resultSet.getString("phone_number")));
    }
}
