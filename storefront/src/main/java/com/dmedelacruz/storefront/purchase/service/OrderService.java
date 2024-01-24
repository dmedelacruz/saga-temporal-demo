package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import com.dmedelacruz.storemodel.order.OrderStatus;
import com.dmedelacruz.storemodel.order.UpdateOrderResponse;

public interface OrderService {
    CreateOrderResponse requestOrderCreation(CreateOrderRequest createOrderRequest);
    UpdateOrderResponse updateOrderStatus(String orderId, OrderStatus orderStatus);
}
