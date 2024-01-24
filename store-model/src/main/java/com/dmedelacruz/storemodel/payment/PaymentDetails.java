package com.dmedelacruz.storemodel.payment;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "paymentMethod"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CashPaymentDetails.class, name = "CASH"),
        @JsonSubTypes.Type(value = CardPaymentDetails.class, name = "CARD"),
        @JsonSubTypes.Type(value = WalletPaymentDetails.class, name = "WALLET")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class PaymentDetails {
    private String paymentMethod;
    private String customerId;
    private String orderId;
    private Double amountPaid;
}
