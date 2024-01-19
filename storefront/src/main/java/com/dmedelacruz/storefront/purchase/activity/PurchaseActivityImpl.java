package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storefront.config.TemporalActivityApplicationContext;
import com.dmedelacruz.storefront.purchase.workflow.ProcessOrderWorkflow;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;
import io.temporal.client.WorkflowClient;

public class PurchaseActivityImpl implements PurchaseActivity {

    private final WorkflowClient workflowClient = TemporalActivityApplicationContext.getBean(WorkflowClient.class);

    @Override
    public void processOrder(String orderId, PurchaseRequest purchaseRequest) {
        ProcessOrderWorkflow processOrderWorkflow = workflowClient.newWorkflowStub(ProcessOrderWorkflow.class, ProcessOrderWorkflow.workflowOptions());
        WorkflowClient.start(processOrderWorkflow::processOrder, orderId, purchaseRequest);
    }
}
