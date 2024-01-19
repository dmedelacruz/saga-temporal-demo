package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;

public class InventoryActivityImpl implements InventoryActivity {
    @Override
    public UpdateInventoryResponse updateInventory(UpdateInventoryRequest updateInventoryRequest) {
        try {
            System.out.println("UPDATING INVENTORY.... WAITING");
            Thread.sleep(10000);
            System.out.println("UPDATING INVENTORY OK");
            return null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
