package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storefront.external.restclient.OrderRestClient;
import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.order.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRestClient orderRestClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public CreateOrderResponse requestOrderCreation(CreateOrderRequest createOrderRequest) {
        LOGGER.info("Requesting Order Creation");
        try {

            ResponseEntity<RestResponse<CreateOrderResponse>> response = orderRestClient.createOrder(createOrderRequest);

            if(!response.getStatusCode().is2xxSuccessful()) {
                throw new CreateOrderException("Error Creating Order");
            }

            CreateOrderResponse createdOrder = response.getBody().getContent();

            if(createdOrder.getIsSuccess()) {
                return createdOrder;
            } else {
                throw new CreateOrderException();
            }
        } catch (Exception e) {
            throw new CreateOrderException();
        }
    }

    @Override
    public UpdateOrderResponse updateOrderStatus(String orderId, OrderStatus orderStatus) {
        ResponseEntity<RestResponse<UpdateOrderResponse>> response = orderRestClient.updateOrderStatus(orderId, orderStatus.name());
        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new UpdateOrderException();
        }
        return response.getBody().getContent();
    }
}
