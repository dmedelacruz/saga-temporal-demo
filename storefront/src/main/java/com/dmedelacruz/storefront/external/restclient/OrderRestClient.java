package com.dmedelacruz.storefront.external.restclient;

import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import com.dmedelacruz.storemodel.order.UpdateOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${restclient.order.name}", url = "${restclient.order.url}")
public interface OrderRestClient {

    @PostMapping
    ResponseEntity<RestResponse<CreateOrderResponse>> createOrder(@RequestBody CreateOrderRequest createOrderRequest);

    @PutMapping("/{id}")
    ResponseEntity<RestResponse<UpdateOrderResponse>> updateOrderStatus(@PathVariable("id") String orderId, @RequestParam("status") String status);

}
