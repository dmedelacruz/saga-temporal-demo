package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storefront.config.TemporalActivityApplicationContext;
import com.dmedelacruz.storefront.purchase.service.OrderService;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import com.dmedelacruz.storemodel.order.OrderStatus;
import com.dmedelacruz.storemodel.order.UpdateOrderResponse;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;

public class OrderActivityImpl implements OrderActivity {

    private final OrderService orderService = TemporalActivityApplicationContext.getBean(OrderService.class);

    @Override
    public CreateOrderResponse createOrder(PurchaseRequest purchaseRequest) {
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .customerId(purchaseRequest.getCustomerId())
                .items(purchaseRequest.getItems())
                .build();
        return orderService.requestOrderCreation(createOrderRequest);
    }

    @Override
    public UpdateOrderResponse updateOrderStatus(String orderId, OrderStatus orderStatus) {
        return orderService.updateOrderStatus(orderId, orderStatus);
    }

}
