package com.dmedelacruz.storemodel.storefront;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    private String customerId;
    private List<String> items;
    private String shippingAddressId;
    private String paymentInfoId;
}