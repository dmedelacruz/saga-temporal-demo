package com.dmedelacruz.inventoryservice.service;

import com.dmedelacruz.inventoryservice.constant.TransactionType;
import com.dmedelacruz.inventoryservice.entity.Item;
import com.dmedelacruz.storemodel.inventory.ItemDto;
import com.dmedelacruz.storemodel.inventory.ItemQtyDto;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemWriteService itemWriteService;

    @Override
    public UpdateInventoryResponse processPurchase(List<ItemQtyDto> items) {
        List<Item> updatedItemsList = itemWriteService.updateItemStock(items, TransactionType.PURCHASE);
        List<ItemDto> itemsList = updatedItemsList.stream().map(item -> ItemDto.builder().itemId(item.getId()).description(item.getDescription()).stock(item.getStock()).build()).toList();
        return UpdateInventoryResponse.builder().items(itemsList).build();
    }

    @Override
    public UpdateInventoryResponse reversePurchase(List<ItemQtyDto> items) {
        List<Item> updatedItemsList = itemWriteService.updateItemStock(items, TransactionType.PROCURE);
        List<ItemDto> itemsList = updatedItemsList.stream().map(item -> ItemDto.builder().itemId(item.getId()).description(item.getDescription()).stock(item.getStock()).build()).toList();
        return UpdateInventoryResponse.builder().items(itemsList).build();
    }
}
