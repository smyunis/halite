package com.smyunis.halite.application.billing;

import com.smyunis.halite.application.domaineventhandlers.DomainEventHandler;
import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillData;
import com.smyunis.halite.domain.billing.BillId;
import com.smyunis.halite.domain.billing.BillRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.domainevents.OrderCreatedEvent;

public class BillOrderCreatedEventHandler implements DomainEventHandler<OrderCreatedEvent> {
    private final BillRepository billRepository;

    public BillOrderCreatedEventHandler(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public void handleEvent(OrderCreatedEvent domainEvent) {
        Order createdOrder = domainEvent.getCreatedOrder();
        Bill bill = createBill(createdOrder);
        billRepository.save(bill);
    }

    private Bill createBill(Order createdOrder) {
        BillData billData = new BillData()
                .setPayeeId(createdOrder.getCatererId())
                .setPayerId(createdOrder.getCateringEventHostId());
        return new Bill(billData);
    }

}
