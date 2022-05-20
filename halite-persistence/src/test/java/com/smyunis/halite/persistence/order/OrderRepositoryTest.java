package com.smyunis.halite.persistence.order;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.OrderId;
import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.domain.order.bill.Bill;
import com.smyunis.halite.domain.order.bill.BillData;
import com.smyunis.halite.domain.order.bill.BillId;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderRepositoryTest {
    private static final JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();
    private final OrderRepository repository = new OrderRepositoryImpl(jdbcTemplate);
    private OrderId orderId;
    private OrderData orderData;
    private Order order;
    private BillData billData;

    @BeforeEach
    void setup() {
        orderId = new OrderId();
        billData = new BillData()
                .setId(new BillId())
                .setOutstandingAmount(new MonetaryAmount(5000))
                .setDueDateTime(LocalDateTime.now().plusMonths(2))
                .setRemark("To be paid in full! Or else ...");
        Bill bill = new Bill(billData);

        Map<CateringMenuItemId, Integer> orderedItems = new HashMap<>();
        orderedItems.put(new CateringMenuItemId(), 10);
        orderedItems.put(new CateringMenuItemId(), 11);
        orderedItems.put(new CateringMenuItemId(), 12);
        orderedItems.put(new CateringMenuItemId(), 13);

        orderData = new OrderData()
                .setId(orderId)
                .setCatererId(new CatererId())
                .setCateringEventHostId(new CateringEventHostId())
                .setCateringEventId(new CateringEventId())
                .setBill(bill)
                .setOrderedCateringMenuItems(orderedItems);

        order = new Order(orderData);
    }

    @Test
    void canSaveThenGetOrder() {

        repository.save(order);

        Order fetchedOrder = repository.get(orderId);
        var fetchedOrderData = fetchedOrder.getDataReadOnlyProxy();
        var fetchedOrderBillData = fetchedOrderData.getBill().getDataReadOnlyProxy();


        assertEquals(orderData.getId(), fetchedOrderData.getId());
        assertEquals(orderData.getCatererId(), fetchedOrderData.getCatererId());
        assertEquals(orderData.getStatus(), fetchedOrderData.getStatus());
        assertEquals(orderData.getOrderedCateringMenuItems(), fetchedOrderData.getOrderedCateringMenuItems());
        assertEquals(orderData.getCateringEventId(), fetchedOrderData.getCateringEventId());
        assertEquals(orderData.getCateringEventHostId(), fetchedOrderData.getCateringEventHostId());

        assertEquals(billData.getId(), fetchedOrderBillData.getId());
        assertEquals(billData.getBillStatus(), fetchedOrderBillData.getBillStatus());
        assertEquals(billData.getDueDateTime().toEpochSecond(ZoneOffset.UTC), fetchedOrderBillData.getDueDateTime().toEpochSecond(ZoneOffset.UTC));
        assertEquals(billData.getRemark(), fetchedOrderBillData.getRemark());
    }
}
