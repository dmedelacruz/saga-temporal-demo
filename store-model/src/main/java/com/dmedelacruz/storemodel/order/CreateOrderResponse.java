package com.dmedelacruz.storemodel.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private String customerId;
    private String orderId;
    private Boolean isSuccess;
}
