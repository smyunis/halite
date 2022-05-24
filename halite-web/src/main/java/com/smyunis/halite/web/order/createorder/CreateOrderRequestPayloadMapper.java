package com.smyunis.halite.web.order.createorder;

import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostId;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.web.Mapper;

public class CreateOrderRequestPayloadMapper implements Mapper<CreateOrderRequestPayload, OrderData> {
    @Override
    public OrderData map(CreateOrderRequestPayload obj) {
        return new OrderData()
                .setCatererId(new CatererId(obj.getCatererId()))
                .setCateringEventHostId(new CateringEventHostId(obj.getCateringEventHostId()))
                .setCateringEventId(new CateringEventId(obj.getCateringEventId()));
    }
}
