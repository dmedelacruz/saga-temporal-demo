package com.dmedelacruz.inventoryservice.service;

import com.dmedelacruz.storemodel.inventory.ItemDto;
import com.dmedelacruz.storemodel.inventory.ItemQtyDto;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;

import java.util.List;

public interface ItemService {

    UpdateInventoryResponse processPurchase(List<ItemQtyDto> items);
    UpdateInventoryResponse reversePurchase(List<ItemQtyDto> items);

}
