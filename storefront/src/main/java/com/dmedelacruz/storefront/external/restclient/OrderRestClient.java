package com.dmedelacruz.storefront.external.restclient;

import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.order.CreateOrderRequest;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${restclient.order.name}", url = "${restclient.order.url}")
public interface OrderRestClient {

    @PostMapping("/order")
    ResponseEntity<RestResponse<CreateOrderResponse>> createOrder(@RequestBody CreateOrderRequest createOrderRequest);

}
