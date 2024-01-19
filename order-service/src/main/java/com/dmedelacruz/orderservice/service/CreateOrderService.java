package com.dmedelacruz.orderservice.service;

import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;

public interface CreateOrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

}
