package com.dmedelacruz.storemodel.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderResponse {
    private String orderId;
    private OrderStatus status;
}
