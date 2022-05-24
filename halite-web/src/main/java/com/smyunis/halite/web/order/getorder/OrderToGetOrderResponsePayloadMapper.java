package com.smyunis.halite.web.order.getorder;

import com.smyunis.halite.domain.order.OrderDataReadOnlyProxy;
import com.smyunis.halite.domain.order.bill.BillDataReadOnlyProxy;
import com.smyunis.halite.web.Mapper;
import com.smyunis.halite.web.order.getorder.GetOrderResponsePayload;

import java.util.List;
import java.util.stream.Collectors;

public class OrderToGetOrderResponsePayloadMapper implements Mapper<OrderDataReadOnlyProxy, GetOrderResponsePayload> {

    @Override
    public GetOrderResponsePayload map(OrderDataReadOnlyProxy obj) {
        return mapToGetOrderResponsePayload(obj);
    }

    private GetOrderResponsePayload mapToGetOrderResponsePayload(OrderDataReadOnlyProxy orderData) {
        var billData = orderData.getBill().getDataReadOnlyProxy();
        var responsePayload = new GetOrderResponsePayload();
        return responsePayload
                .setId(orderData.getId().toString())
                .setOrderStatus(orderData.getStatus().toString())
                .setCatererId(orderData.getCatererId().toString())
                .setOrderedMenuItems(getOrderedMenuItems(orderData, responsePayload))
                .setCateringEventHostId(orderData.getCateringEventHostId().toString())
                .setCateringEventId(orderData.getCateringEventId().toString())
                .setBill(getBillResponsePayload(billData, responsePayload));
    }

    private List<GetOrderResponsePayload.OrderedCateringMenuItemResponsePayload> getOrderedMenuItems(OrderDataReadOnlyProxy orderData, GetOrderResponsePayload responsePayload) {
        return orderData.getOrderedCateringMenuItems().keySet()
                .stream()
                .map(itemId -> responsePayload.new OrderedCateringMenuItemResponsePayload()
                        .setOrderedMenuItemId(itemId.toString())
                        .setOrderedQuantity(orderData.getOrderedCateringMenuItems().get(itemId)))
                .collect(Collectors.toList());
    }

    private GetOrderResponsePayload.BillResponsePayload getBillResponsePayload(BillDataReadOnlyProxy billData, GetOrderResponsePayload responsePayload) {
        return responsePayload.new BillResponsePayload()
                .setId(billData.getId().toString())
                .setBillStatus(billData.getBillStatus().toString())
                .setOutstandingAmount(billData.getOutstandingAmount().amount())
                .setDueDateTime(billData.getDueDateTime())
                .setSettlementDateTime(billData.getSettlementDateTime())
                .setRemark(billData.getRemark());
    }


}
