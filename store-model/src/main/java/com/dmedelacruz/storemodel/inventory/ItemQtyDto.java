package com.dmedelacruz.storemodel.inventory;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemQtyDto {
    private String itemId;
    private Integer qty;
}
