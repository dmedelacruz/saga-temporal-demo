package com.dmedelacruz.storemodel.inventory;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInventoryResponse {
    private List<ItemDto> items;
}