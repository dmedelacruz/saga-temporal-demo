package com.dmedelacruz.storemodel.inventory;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String itemId;
    private String description;
    private Integer stock;
}
