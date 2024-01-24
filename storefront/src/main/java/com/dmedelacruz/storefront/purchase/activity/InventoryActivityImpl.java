package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storefront.config.TemporalActivityApplicationContext;
import com.dmedelacruz.storefront.purchase.service.InventoryService;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;
import io.temporal.client.WorkflowClient;

public class InventoryActivityImpl implements InventoryActivity {

    private final InventoryService inventoryService = TemporalActivityApplicationContext.getBean(InventoryService.class);

    @Override
    public UpdateInventoryResponse updateInventory(UpdateInventoryRequest updateInventoryRequest) {
        return inventoryService.updateInventoryForPurchase(updateInventoryRequest);
    }

    @Override
    public UpdateInventoryResponse updateInventoryReversePurchase(UpdateInventoryRequest updateInventoryRequest) {
        return inventoryService.reverseInventoryForPurchase(updateInventoryRequest);
    }
}
