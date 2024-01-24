package com.dmedelacruz.storemodel.inventory;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInventoryRequest {
    private List<ItemQtyDto> items;
}
