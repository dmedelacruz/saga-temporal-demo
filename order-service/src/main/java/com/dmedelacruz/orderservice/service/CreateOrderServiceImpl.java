package com.dmedelacruz.orderservice.service;

import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {
    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        //TODO do something
        System.out.println("CREATING ORDER");
        String orderId = UUID.randomUUID().toString();
        return CreateOrderResponse.builder()
                .customerId(createOrderRequest.getCustomerId())
                .orderId(orderId)
                .isSuccess(true)
                .build();
    }
}
