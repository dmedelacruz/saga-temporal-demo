package com.dmedelacruz.orderservice.api;

import com.dmedelacruz.orderservice.service.OrderService;
import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import com.dmedelacruz.storemodel.order.UpdateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<RestResponse<CreateOrderResponse>> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderResponse createdOrder = orderService.createOrder(createOrderRequest);
        return ResponseEntity.ok(RestResponse.of(createdOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UpdateOrderResponse>> updateOrderStatus(@PathVariable("id") String orderId, @RequestParam("status") String status) {
        UpdateOrderResponse updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(RestResponse.of(updatedOrder));
    }

}
