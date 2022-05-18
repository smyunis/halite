package com.smyunis.halite.persistence.caterer;

import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.shared.PhoneNumber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatererRepositoryTest {

    private static CatererRepositoryImpl catererRepository;

    @BeforeAll
    static void dbSetup() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://127.0.0.1:5432/halite_test");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("123456seven");
        DataSource dataSource = dataSourceBuilder.build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        catererRepository = new CatererRepositoryImpl(jdbcTemplate);
    }

    @Test
    void catererRepoTest() {

        Caterer caterer = new Caterer(new CatererData().setName("T Cat").setPhoneNumber(new PhoneNumber("+2519354196")));

        var x = catererRepository.get(new CatererId("9fabdeca-fb57-4ec8-831e-ee81f0cbd011"));

        assertEquals(0, x.getRecommendationMetric());
    }
}
