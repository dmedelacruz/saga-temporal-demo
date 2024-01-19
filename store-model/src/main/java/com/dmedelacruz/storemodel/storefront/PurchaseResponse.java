package com.dmedelacruz.storemodel.storefront;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    private String customerId;
    private String orderId;
    private String workflowId;
}
