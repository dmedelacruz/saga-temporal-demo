package com.dmedelacruz.orderservice.service;

import com.dmedelacruz.orderservice.entity.Order;
import com.dmedelacruz.storemodel.order.OrderStatus;
import com.dmedelacruz.orderservice.repository.OrderRepository;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.OrderNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderWriteServiceImpl implements OrderWriteService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .dateCreated(LocalDateTime.now())
                .orderStatus(OrderStatus.CREATED)
                .build();
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }
}
