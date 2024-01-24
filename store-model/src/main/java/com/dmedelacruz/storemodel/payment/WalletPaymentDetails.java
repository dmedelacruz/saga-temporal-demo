package com.dmedelacruz.storemodel.payment;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PaymentDetails")
public class WalletPaymentDetails extends PaymentDetails {
    private String walletId;
}
