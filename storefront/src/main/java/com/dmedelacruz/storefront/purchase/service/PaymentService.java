package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storemodel.payment.PaymentRequest;

public interface PaymentService {

    Boolean processPayment(PaymentRequest paymentRequest);

}
