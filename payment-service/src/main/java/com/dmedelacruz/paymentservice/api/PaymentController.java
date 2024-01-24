package com.dmedelacruz.paymentservice.api;

import com.dmedelacruz.paymentservice.service.PaymentService;
import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.payment.PaymentDetails;
import com.dmedelacruz.storemodel.payment.PaymentMethod;
import com.dmedelacruz.storemodel.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<RestResponse<Boolean>> processPayment(@RequestBody PaymentRequest paymentRequest) {
        Boolean success = paymentService.processPayment(paymentRequest.getPaymentDetails(), paymentRequest.getPaymentMethod());
        return ResponseEntity.ok(RestResponse.of(success));
    }

}
