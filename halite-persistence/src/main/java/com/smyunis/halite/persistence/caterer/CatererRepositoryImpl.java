package com.smyunis.halite.persistence.caterer;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.shared.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CatererRepositoryImpl implements CatererRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CatererRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Caterer get(CatererId id) {
        try {
            return tryGetCaterer(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(id.toString(), exception);
        }
    }

    private Caterer tryGetCaterer(CatererId id) {
        String sql = "SELECT * FROM caterer WHERE caterer_id = ?";
        CatererData catererData = jdbcTemplate.queryForObject(sql, this::mapCatererData, id.toString());
        return new Caterer(catererData);
    }

    @Override
    public void save(Caterer domainEntity) {
        var data = domainEntity.getDataReadOnlyProxy();
        String upsertSql = """
                INSERT INTO caterer
                VALUES (?,?,?,?,?,?)
                ON CONFLICT (caterer_id) DO UPDATE
                SET name = excluded.name,
                    phone_number = excluded.phone_number,
                    personal_description = excluded.personal_description,         
                    caterer_image = excluded.caterer_image,         
                    recommendation_metric = excluded.recommendation_metric;
                """;
        jdbcTemplate.update(upsertSql,
                data.getId().toString(),
                data.getName(),
                data.getPhoneNumber().phoneNumber(),
                data.getPersonalDescription(),
                data.getCatererImage().toString(),
                data.getRecommendationMetric());
    }


    private CatererData mapCatererData(ResultSet resultSet, int row) {
        try {
            return new CatererData()
                    .setId(new CatererId(resultSet.getString("caterer_id")))
                    .setName(resultSet.getString("name"))
                    .setPhoneNumber(new PhoneNumber(resultSet.getString("phone_number")))
                    .setPersonalDescription(resultSet.getString("personal_description"))
                    .setCatererImage(new URL(resultSet.getString("caterer_image")))
                    .setRecommendationMetric(resultSet.getInt("recommendation_metric"));
        } catch (MalformedURLException ex) {
            throw new InvalidValueException(ex.getMessage(), ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
