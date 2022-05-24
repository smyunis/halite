package com.smyunis.halite.web.order.createorder;

public class CreateOrderRequestPayload {
    private String cateringEventId;
    private String cateringEventHostId;
    private String catererId;

    public String getCateringEventId() {
        return cateringEventId;
    }

    public CreateOrderRequestPayload setCateringEventId(String cateringEventId) {
        this.cateringEventId = cateringEventId;
        return this;
    }

    public String getCateringEventHostId() {
        return cateringEventHostId;
    }

    public CreateOrderRequestPayload setCateringEventHostId(String cateringEventHostId) {
        this.cateringEventHostId = cateringEventHostId;
        return this;
    }

    public String getCatererId() {
        return catererId;
    }

    public CreateOrderRequestPayload setCatererId(String catererId) {
        this.catererId = catererId;
        return this;
    }
}
