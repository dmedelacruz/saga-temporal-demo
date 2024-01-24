package com.dmedelacruz.inventoryservice.service;

import com.dmedelacruz.storemodel.inventory.ItemDto;
import com.dmedelacruz.storemodel.inventory.ItemQtyDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> processPurchase(List<ItemQtyDto> items);
    List<ItemDto> reversePurchase(List<ItemQtyDto> items);

}
