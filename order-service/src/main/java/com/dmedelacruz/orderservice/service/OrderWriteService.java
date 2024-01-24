package com.dmedelacruz.orderservice.service;

import com.dmedelacruz.orderservice.entity.Order;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;

public interface OrderWriteService {

    Order createOrder(CreateOrderRequest request);
    Order updateOrderStatus(String orderId, String status);

}
