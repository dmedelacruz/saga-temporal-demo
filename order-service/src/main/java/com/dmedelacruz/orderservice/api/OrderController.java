package com.dmedelacruz.orderservice.api;

import com.dmedelacruz.orderservice.service.CreateOrderService;
import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderService createOrderService;

    @PostMapping
    public ResponseEntity<RestResponse<CreateOrderResponse>> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderResponse createdOrder = createOrderService.createOrder(createOrderRequest);
        return ResponseEntity.ok(RestResponse.of(createdOrder));
    }

}
