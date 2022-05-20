package com.smyunis.halite.persistence.order;

import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderId;
import com.smyunis.halite.domain.order.OrderRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order get(OrderId id) {
        return null;
    }

    @Override
    public void save(Order entity) {

    }

    @Override
    public void removeMenuItemFromAllOrdersPendingAcceptanceByMenuItemId(CateringMenuItemId cateringMenuItemId) {

    }

    @Override
    public void cancelAllOrdersByCateringEventId(CateringEventId cateringEventId) {

    }
}
