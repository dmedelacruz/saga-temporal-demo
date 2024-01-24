package com.dmedelacruz.inventoryservice.service;

import com.dmedelacruz.inventoryservice.constant.TransactionType;
import com.dmedelacruz.inventoryservice.entity.Item;
import com.dmedelacruz.inventoryservice.repository.ItemRepository;
import com.dmedelacruz.storemodel.inventory.ItemNotFoundException;
import com.dmedelacruz.storemodel.inventory.ItemOutOfStockException;
import com.dmedelacruz.storemodel.inventory.ItemQtyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemWriteServiceImpl implements ItemWriteService {

    private final ItemRepository itemRepository;

    @Override
    public Item updateItemStock(String itemId, TransactionType transactionType, Integer qty) {
        Item item;
        switch (transactionType) {
            case PURCHASE -> item = processPurchase(itemId, qty);
            case PROCURE -> item =  processProcure(itemId, qty);
            default -> throw new UnsupportedOperationException();
        }
        return itemRepository.save(item);
    }

    @Override
    public List<Item> updateItemStock(List<ItemQtyDto> items, TransactionType transactionType) {
        List<Item> itemsList = new ArrayList<>();
        switch (transactionType) {
            case PURCHASE -> itemsList = items.stream().map(item -> processPurchase(item.getItemId(), item.getQty())).collect(Collectors.toList());
            case PROCURE -> itemsList = items.stream().map(item -> processProcure(item.getItemId(), item.getQty())).collect(Collectors.toList());
            default -> throw new UnsupportedOperationException();
        }
        return itemRepository.saveAll(itemsList);
    }

    private Item processPurchase(String itemId, Integer qty) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        if(item.getStock() < qty) {
            throw new ItemOutOfStockException();
        }
        int newStock = item.getStock() - qty;
        item.setStock(newStock);
        return item;
    }

    private Item processProcure(String itemId, Integer qty) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        int newStock = item.getStock() + qty;
        item.setStock(newStock);
        return item;
    }
}
