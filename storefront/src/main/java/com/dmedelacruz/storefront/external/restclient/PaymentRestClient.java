package com.dmedelacruz.storefront.external.restclient;

import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.payment.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${restclient.payment.name}", url = "${restclient.payment.url}")
public interface PaymentRestClient {

    @PostMapping
    ResponseEntity<RestResponse<Boolean>> processPayment(@RequestBody PaymentRequest paymentRequest);

}
