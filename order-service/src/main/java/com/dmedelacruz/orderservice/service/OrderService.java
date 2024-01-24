package com.dmedelacruz.orderservice.service;

import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import com.dmedelacruz.storemodel.order.UpdateOrderResponse;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
    UpdateOrderResponse updateOrderStatus(String orderId, String status);
}
