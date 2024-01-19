package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storefront.external.restclient.OrderRestClient;
import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.order.CreateOrderException;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
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
                throw new CreateOrderException("Error Creating Order");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CreateOrderException("Error Creating Order");
        }
    }
}
