package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storefront.external.restclient.PaymentRestClient;
import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRestClient paymentRestClient;

    @Override
    public Boolean processPayment(PaymentRequest paymentRequest) {
        try {
            ResponseEntity<RestResponse<Boolean>> response = paymentRestClient.processPayment(paymentRequest);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody().getContent();
            } else {
                //TODO throw ActivityFailure
                return null;
            }
        } catch (Exception e) {
            //TODO throw ActivityFailure
            return null;
        }
    }
}
