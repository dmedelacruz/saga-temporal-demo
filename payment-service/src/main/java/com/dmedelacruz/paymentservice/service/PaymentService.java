package com.dmedelacruz.paymentservice.service;

import com.dmedelacruz.storemodel.payment.PaymentDetails;
import com.dmedelacruz.storemodel.payment.PaymentMethod;

public interface PaymentService {
    <T extends PaymentDetails> Boolean processPayment(T paymentDetails, PaymentMethod paymentMethod);
}
