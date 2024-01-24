package com.dmedelacruz.storemodel.payment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private PaymentDetails paymentDetails;
    private PaymentMethod paymentMethod;
}
