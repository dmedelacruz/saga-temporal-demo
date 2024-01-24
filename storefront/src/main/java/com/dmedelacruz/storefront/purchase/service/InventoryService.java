package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;

public interface InventoryService {

    UpdateInventoryResponse updateInventoryForPurchase(UpdateInventoryRequest updateInventoryRequest);
    UpdateInventoryResponse reverseInventoryForPurchase(UpdateInventoryRequest updateInventoryRequest);
}
