package com.smyunis.halite.persistence.caterer;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.shared.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CatererRepositoryImpl implements CatererRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CatererRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Caterer get(CatererId id) {
        String sql = "SELECT * FROM caterer WHERE caterer_id = ?";
       CatererData catererData = jdbcTemplate.queryForObject(sql, this::mapCatererData, id.toString());

        if (catererData == null)
            throw new NoSuchElementException(String.format("CatererId : %s Does Not Exist", id.toString()));

        return new Caterer(catererData);
    }

    @Override
    public void save(Caterer domainEntity) {
        var data = domainEntity.getDataReadOnlyProxy();
        String upsertSql = """
                INSERT INTO caterer
                VALUES (?,?,?,?)
                ON CONFLICT (caterer_id) DO UPDATE
                SET name = excluded.name,
                    phone_number = excluded.phone_number,
                    recommendation_metric = excluded.recommendation_metric;
                """;
        jdbcTemplate.update(upsertSql,
                data.getId().toString(),
                data.getName(),
                data.getPhoneNumber().phoneNumber(),
                data.getRecommendationMetric());
    }


    private CatererData mapCatererData(ResultSet resultSet, int row) throws SQLException {
        return new CatererData()
                .setId(new CatererId(resultSet.getString("caterer_id")))
                .setName(resultSet.getString("name"))
                .setPhoneNumber(new PhoneNumber(resultSet.getString("phone_number")))
                .setRecommendationMetric(resultSet.getInt("recommendation_metric"));
    }
}
