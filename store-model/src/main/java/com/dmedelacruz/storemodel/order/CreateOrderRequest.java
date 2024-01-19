package com.dmedelacruz.storemodel.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private String customerId;
    private List<String> items;
}
