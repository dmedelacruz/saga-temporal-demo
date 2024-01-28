package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storefront.config.TemporalActivityApplicationContext;
import com.dmedelacruz.storefront.purchase.service.OrderService;
import com.dmedelacruz.storemodel.inventory.ItemQtyDto;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import com.dmedelacruz.storemodel.order.OrderStatus;
import com.dmedelacruz.storemodel.order.UpdateOrderResponse;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;

import java.util.List;

public class OrderActivityImpl implements OrderActivity {

    private final OrderService orderService = TemporalActivityApplicationContext.getBean(OrderService.class);

    @Override
    public CreateOrderResponse createOrder(PurchaseRequest purchaseRequest) {
        List<String> items = purchaseRequest.getItems().stream().map(ItemQtyDto::getItemId).toList();
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .customerId(purchaseRequest.getCustomerId())
                .items(items)
                .build();
        return orderService.requestOrderCreation(createOrderRequest);
    }

    @Override
    public UpdateOrderResponse updateOrderStatus(String orderId, OrderStatus orderStatus) {
        return orderService.updateOrderStatus(orderId, orderStatus);
    }

    @Override
    public UpdateOrderResponse cancelOrder(String orderId) {
        return orderService.updateOrderStatus(orderId, OrderStatus.CANCELLED);
    }

}
