package com.smyunis.halite.web.order.getorder;

import java.time.LocalDateTime;
import java.util.List;

public class GetOrderResponsePayload {
    private String id;
    private String orderStatus;
    private String cateringEventId;
    private String cateringEventHostId;
    private String catererId;
    private List<OrderedCateringMenuItemResponsePayload> orderedMenuItems;
    private BillResponsePayload bill;

    public String getId() {
        return id;
    }

    public GetOrderResponsePayload setId(String id) {
        this.id = id;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public GetOrderResponsePayload setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getCateringEventId() {
        return cateringEventId;
    }

    public GetOrderResponsePayload setCateringEventId(String cateringEventId) {
        this.cateringEventId = cateringEventId;
        return this;
    }

    public String getCateringEventHostId() {
        return cateringEventHostId;
    }

    public GetOrderResponsePayload setCateringEventHostId(String cateringEventHostId) {
        this.cateringEventHostId = cateringEventHostId;
        return this;
    }

    public String getCatererId() {
        return catererId;
    }

    public GetOrderResponsePayload setCatererId(String catererId) {
        this.catererId = catererId;
        return this;
    }

    public List<OrderedCateringMenuItemResponsePayload> getOrderedMenuItems() {
        return orderedMenuItems;
    }

    public GetOrderResponsePayload setOrderedMenuItems(List<OrderedCateringMenuItemResponsePayload> orderedMenuItems) {
        this.orderedMenuItems = orderedMenuItems;
        return this;
    }

    public BillResponsePayload getBill() {
        return bill;
    }

    public GetOrderResponsePayload setBill(BillResponsePayload bill) {
        this.bill = bill;
        return this;
    }

    public class OrderedCateringMenuItemResponsePayload {
        private String orderedMenuItemId;
        private int orderedQuantity;

        public String getOrderedMenuItemId() {
            return orderedMenuItemId;
        }

        public OrderedCateringMenuItemResponsePayload setOrderedMenuItemId(String orderedMenuItemId) {
            this.orderedMenuItemId = orderedMenuItemId;
            return this;
        }

        public int getOrderedQuantity() {
            return orderedQuantity;
        }

        public OrderedCateringMenuItemResponsePayload setOrderedQuantity(int orderedQuantity) {
            this.orderedQuantity = orderedQuantity;
            return this;
        }
    }
    public class BillResponsePayload {
        private String id;
        private String billStatus;
        private double outstandingAmount;
        private LocalDateTime dueDateTime;
        private LocalDateTime settlementDateTime;
        private String remark;

        public String getId() {
            return id;
        }

        public BillResponsePayload setId(String id) {
            this.id = id;
            return this;
        }

        public String getBillStatus() {
            return billStatus;
        }

        public BillResponsePayload setBillStatus(String billStatus) {
            this.billStatus = billStatus;
            return this;
        }

        public double getOutstandingAmount() {
            return outstandingAmount;
        }

        public BillResponsePayload setOutstandingAmount(double outstandingAmount) {
            this.outstandingAmount = outstandingAmount;
            return this;
        }

        public LocalDateTime getDueDateTime() {
            return dueDateTime;
        }

        public BillResponsePayload setDueDateTime(LocalDateTime dueDateTime) {
            this.dueDateTime = dueDateTime;
            return this;
        }

        public LocalDateTime getSettlementDateTime() {
            return settlementDateTime;
        }

        public BillResponsePayload setSettlementDateTime(LocalDateTime settlementDateTime) {
            this.settlementDateTime = settlementDateTime;
            return this;
        }

        public String getRemark() {
            return remark;
        }

        public BillResponsePayload setRemark(String remark) {
            this.remark = remark;
            return this;
        }
    }
}
