package com.dmedelacruz.orderservice.service;

import com.dmedelacruz.orderservice.entity.Order;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import com.dmedelacruz.storemodel.order.UpdateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderWriteService orderWriteService;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        LOGGER.info("Creating Order");
        Order order = orderWriteService.createOrder(createOrderRequest);
        return CreateOrderResponse.builder()
                .customerId(createOrderRequest.getCustomerId())
                .orderId(order.getId())
                .status(order.getOrderStatus())
                .isSuccess(true)
                .build();
    }

    @Override
    public UpdateOrderResponse updateOrderStatus(String orderId, String status) {
        Order order = orderWriteService.updateOrderStatus(orderId, status);
        return UpdateOrderResponse.builder()
                .orderId(order.getId())
                .status(order.getOrderStatus())
                .build();
    }
}
