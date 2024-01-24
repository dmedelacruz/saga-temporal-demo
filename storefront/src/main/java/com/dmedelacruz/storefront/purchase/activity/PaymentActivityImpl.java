package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storefront.config.TemporalActivityApplicationContext;
import com.dmedelacruz.storefront.purchase.service.OrderService;
import com.dmedelacruz.storefront.purchase.service.PaymentService;
import com.dmedelacruz.storemodel.payment.PaymentRequest;

public class PaymentActivityImpl implements PaymentActivity {

    private final PaymentService paymentService = TemporalActivityApplicationContext.getBean(PaymentService.class);

    @Override
    public Boolean processPayment(PaymentRequest paymentRequest) {
        return paymentService.processPayment(paymentRequest);
    }
}
