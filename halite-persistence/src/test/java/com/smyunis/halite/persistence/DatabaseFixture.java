package com.smyunis.halite.persistence;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DatabaseFixture {

    public static JdbcTemplate createJdbcTemplate() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://127.0.0.1:5432/halite_test");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("123456seven");
        DataSource dataSource = dataSourceBuilder.build();
        return new JdbcTemplate(dataSource);
    }

}
