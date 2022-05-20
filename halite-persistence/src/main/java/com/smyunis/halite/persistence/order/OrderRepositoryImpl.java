package com.smyunis.halite.persistence.order;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.order.*;
import com.smyunis.halite.domain.order.bill.Bill;
import com.smyunis.halite.domain.order.bill.BillData;
import com.smyunis.halite.domain.order.bill.BillId;
import com.smyunis.halite.domain.order.bill.BillStatus;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order get(OrderId id) {
        try {
            return tryGetOrder(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(id.toString(), exception);
        }
    }

    private Order tryGetOrder(OrderId id) {
        OrderData orderData = fetchOrder(id);
        var orderedItems = fetchOrderedMenuItems(id);
        var bill = fetchOrderBill(id);

        orderData.setBill(bill);
        orderData.setOrderedCateringMenuItems(orderedItems);
        return new Order(orderData);
    }

    private Bill fetchOrderBill(OrderId id) {
        String fetchOrderBillSql = "SELECT * FROM catering_order_bill WHERE catering_order_id = ?;";
        var billData = jdbcTemplate.queryForObject(fetchOrderBillSql, this::mapOrderBill, id.toString());
        return new Bill(billData);
    }

    private BillData mapOrderBill(ResultSet resultSet, int row) throws SQLException {
        return new BillData()
                .setId(new BillId(resultSet.getString("bill_id")))
                .setBillStatus(BillStatus.valueOf(resultSet.getString("status")))
                .setOutstandingAmount(new MonetaryAmount(resultSet.getDouble("outstanding_amount")))
                .setDueDateTime(resultSet.getTimestamp("due_time").toLocalDateTime())
                .setSettlementDateTime(
                        resultSet.getTimestamp("settlement_time") == null ?
                                null :
                                resultSet.getTimestamp("settlement_time").toLocalDateTime()
                )
                .setRemark(resultSet.getString("remark"));
    }

    private Map<CateringMenuItemId, Integer> fetchOrderedMenuItems(OrderId id) {
        String fetchOrderMenuItemsSql = "SELECT catering_menu_item_id,quantity FROM catering_order_menu_items WHERE catering_order_id = ?;";
        Stream<Map.Entry<CateringMenuItemId, Integer>> orderedItemsStream =
                jdbcTemplate.queryForStream(fetchOrderMenuItemsSql, this::mapOrderedMenuItems, id.toString());
        return orderedItemsStream.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map.Entry<CateringMenuItemId, Integer> mapOrderedMenuItems(ResultSet resultSet, int row) throws SQLException {
        CateringMenuItemId cateringMenuItemId = new CateringMenuItemId(resultSet.getString("catering_menu_item_id"));
        Integer quantity = resultSet.getInt("quantity");
        return new AbstractMap.SimpleEntry<>(cateringMenuItemId, quantity);
    }

    private OrderData fetchOrder(OrderId id) {
        String fetchOrderSql = "SELECT * FROM catering_order WHERE catering_order_id = ?;";
        return jdbcTemplate.queryForObject(fetchOrderSql, this::mapOrder, id.toString());
    }

    private OrderData mapOrder(ResultSet resultSet, int row) throws SQLException {
        return new OrderData()
                .setId(new OrderId(resultSet.getString("catering_order_id")))
                .setStatus(OrderStatus.valueOf(resultSet.getString("status")))
                .setCateringEventId(new CateringEventId(resultSet.getString("catering_event_id")))
                .setCateringEventHostId((new CateringEventHostId(resultSet.getString("catering_event_host_id"))))
                .setCatererId(new CatererId(resultSet.getString("caterer_id")));
    }

    @Override
    public void save(Order entity) {
        var data = entity.getDataReadOnlyProxy();
        upsertOrder(data);
        upsertOrderedMenuItems(data);
        upsertOrderBill(data);
    }

    private void upsertOrderBill(OrderDataReadOnlyProxy data) {
        String upsertOrderBillSql = """
                INSERT INTO catering_order_bill
                VALUES (?,?,?,?,?,?,?)
                ON CONFLICT (bill_id) DO UPDATE
                SET catering_order_id = excluded.catering_order_id,
                    status = excluded.status,
                    outstanding_amount = excluded.outstanding_amount,
                    due_time = excluded.due_time,
                    settlement_time = excluded.settlement_time,
                    remark = excluded.remark;  
                """;

        var billData = data.getBill().getDataReadOnlyProxy();
        jdbcTemplate.update(upsertOrderBillSql,
                billData.getId().toString(),
                data.getId().toString(),
                billData.getBillStatus().toString(),
                billData.getOutstandingAmount().amount(),
                billData.getDueDateTime(),
                billData.getSettlementDateTime(),
                billData.getRemark()
        );
    }

    private void upsertOrderedMenuItems(OrderDataReadOnlyProxy data) {
        var namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String upsertOrderMenuItemsSql = """
                INSERT INTO catering_order_menu_items
                SELECT :order_id AS catering_order_id, :menu_item_id AS catering_menu_item_id, :quantity AS quantity
                WHERE NOT EXISTS(
                    SELECT *
                    FROM catering_order_menu_items
                    WHERE catering_order_id = :order_id AND catering_menu_item_id = :menu_item_id AND quantity = :quantity
                )
                LIMIT 1;
                """;

        var orderedMenuItems = data.getOrderedCateringMenuItems();
        MapSqlParameterSource[] orderMenuItemsSqlParameters = orderedMenuItems.keySet().stream()
                .map(menuItemId -> new MapSqlParameterSource()
                        .addValue("order_id", data.getId().toString())
                        .addValue("menu_item_id", menuItemId.toString())
                        .addValue("quantity", orderedMenuItems.get(menuItemId)))
                .toArray(MapSqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(upsertOrderMenuItemsSql, orderMenuItemsSqlParameters);
    }

    private void upsertOrder(OrderDataReadOnlyProxy data) {
        String upsertOrderSql = """
                INSERT INTO catering_order
                VALUES (?,?,?,?,?)
                ON CONFLICT (catering_order_id) DO UPDATE
                SET status = excluded.status,
                    catering_event_id = excluded.catering_event_id,
                    catering_event_host_id = excluded.catering_event_host_id,
                    caterer_id = excluded.caterer_id;                  
                """;
        jdbcTemplate.update(upsertOrderSql,
                data.getId().toString(),
                data.getStatus().toString(),
                data.getCateringEventId().toString(),
                data.getCateringEventHostId().toString(),
                data.getCatererId().toString()
        );
    }

    @Override
    public void removeMenuItemFromAllOrdersPendingAcceptanceByMenuItemId(CateringMenuItemId cateringMenuItemId) {

    }

    @Override
    public void cancelAllOrdersByCateringEventId(CateringEventId cateringEventId) {

    }
}
