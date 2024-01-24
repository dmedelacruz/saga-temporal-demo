package com.dmedelacruz.inventoryservice.api;

import com.dmedelacruz.inventoryservice.service.ItemService;
import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.inventory.ItemDto;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PutMapping("/purchase")
    public ResponseEntity<RestResponse<UpdateInventoryResponse>> updateItemsForPurchase(@RequestBody UpdateInventoryRequest request) {
        List<ItemDto> processedPurchase = itemService.processPurchase(request.getItems());
        return ResponseEntity.ok(RestResponse.of(processedPurchase));
    }

    @PutMapping("/reverse-purchase")
    public ResponseEntity<RestResponse<UpdateInventoryResponse>> reverseItemsPurchase(@RequestBody UpdateInventoryRequest request) {
        List<ItemDto> processedReversePurchase = itemService.reversePurchase(request.getItems());
        return ResponseEntity.ok(RestResponse.of(processedReversePurchase));
    }

}
