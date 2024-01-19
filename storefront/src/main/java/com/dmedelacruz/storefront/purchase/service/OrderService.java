package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;

public interface OrderService {
    CreateOrderResponse requestOrderCreation(CreateOrderRequest createOrderRequest);
}
