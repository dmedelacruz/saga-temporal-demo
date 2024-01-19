package com.dmedelacruz.storefront.purchase.workflow;

import com.dmedelacruz.storefront.purchase.activity.InventoryActivity;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;

public class ProcessOrderWorkflowImpl implements ProcessOrderWorkflow {

    private final Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
    private final Saga saga = new Saga(sagaOptions);

    private final InventoryActivity inventoryActivity = Workflow.newActivityStub(InventoryActivity.class, InventoryActivity.inventoryActivityOptions);

    @Override
    public void processOrder(String orderId, PurchaseRequest purchaseRequest) {
        inventoryActivity.updateInventory(new UpdateInventoryRequest());
    }
}
