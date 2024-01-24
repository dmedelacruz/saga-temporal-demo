package com.dmedelacruz.paymentservice.service;

import com.dmedelacruz.paymentservice.entity.Payment;
import com.dmedelacruz.paymentservice.repository.PaymentRepository;
import com.dmedelacruz.storemodel.payment.PaymentDetails;
import com.dmedelacruz.storemodel.payment.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public <T extends PaymentDetails> Boolean processPayment(T paymentDetails, PaymentMethod paymentMethod) {

        if(paymentMethod.equals(PaymentMethod.WALLET)) {

            Payment payment = Payment.builder()
                    .customerId(paymentDetails.getCustomerId())
                    .orderId(paymentDetails.getOrderId())
                    .amount(paymentDetails.getAmountPaid())
                    .paymentMethod(paymentMethod)
                    .isSuccessful(false)
                    .reason("Wallet Payment Unsupported")
                    .build();

            paymentRepository.save(payment);

            throw new UnsupportedOperationException();
        }

        if(paymentMethod.equals(PaymentMethod.CARD)) {

            boolean isSuccessful = paymentDetails.getAmountPaid() <= 10000;
            String reason = isSuccessful ? null : "Insufficient Funds";

            Payment payment = Payment.builder()
                    .customerId(paymentDetails.getCustomerId())
                    .orderId(paymentDetails.getOrderId())
                    .amount(paymentDetails.getAmountPaid())
                    .paymentMethod(paymentMethod)
                    .isSuccessful(isSuccessful)
                    .reason(reason)
                    .build();

            paymentRepository.save(payment);

            if(!isSuccessful) {
                throw new UnsupportedOperationException();
            }

            return true;
        }

        if(paymentMethod.equals(PaymentMethod.CASH)) {

            Payment payment = Payment.builder()
                    .customerId(paymentDetails.getCustomerId())
                    .orderId(paymentDetails.getOrderId())
                    .amount(paymentDetails.getAmountPaid())
                    .paymentMethod(paymentMethod)
                    .isSuccessful(true)
                    .build();

            paymentRepository.save(payment);

            return true;
        }

        throw new UnsupportedOperationException();
    }
}
