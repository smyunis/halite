package com.smyunis.halite.persistence.order;

import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderRepositoryTest {
    private static final JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();
    private final OrderRepository repository = new OrderRepositoryImpl(jdbcTemplate);


}
