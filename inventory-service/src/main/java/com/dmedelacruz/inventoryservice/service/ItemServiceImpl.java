package com.dmedelacruz.inventoryservice.service;

import com.dmedelacruz.inventoryservice.constant.TransactionType;
import com.dmedelacruz.inventoryservice.entity.Item;
import com.dmedelacruz.storemodel.inventory.ItemDto;
import com.dmedelacruz.storemodel.inventory.ItemQtyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemWriteService itemWriteService;

    @Override
    public List<ItemDto> processPurchase(List<ItemQtyDto> items) {
        List<Item> updatedItemsList = itemWriteService.updateItemStock(items, TransactionType.PURCHASE);
        return updatedItemsList.stream().map(item -> ItemDto.builder().itemId(item.getId()).description(item.getDescription()).stock(item.getStock()).build()).toList();
    }

    @Override
    public List<ItemDto> reversePurchase(List<ItemQtyDto> items) {
        List<Item> updatedItemsList = itemWriteService.updateItemStock(items, TransactionType.PROCURE);
        return updatedItemsList.stream().map(item -> ItemDto.builder().itemId(item.getId()).description(item.getDescription()).stock(item.getStock()).build()).toList();
    }
}
