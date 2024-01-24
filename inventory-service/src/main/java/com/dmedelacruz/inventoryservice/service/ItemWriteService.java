package com.dmedelacruz.inventoryservice.service;

import com.dmedelacruz.inventoryservice.constant.TransactionType;
import com.dmedelacruz.inventoryservice.entity.Item;
import com.dmedelacruz.storemodel.inventory.ItemQtyDto;

import java.util.List;

public interface ItemWriteService {

    Item updateItemStock(String itemId, TransactionType transactionType, Integer qty);
    List<Item> updateItemStock(List<ItemQtyDto> items, TransactionType transactionType);

}
