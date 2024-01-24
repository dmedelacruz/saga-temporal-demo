package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storefront.config.TemporalActivityApplicationContext;
import com.dmedelacruz.storefront.purchase.workflow.ProcessOrderWorkflow;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;
import io.temporal.client.WorkflowClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PurchaseActivityImpl implements PurchaseActivity {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseActivityImpl.class);

    private final WorkflowClient workflowClient = TemporalActivityApplicationContext.getBean(WorkflowClient.class);

    @Override
    public void processOrder(String orderId, PurchaseRequest purchaseRequest) {
        LOGGER.info("Processing Order");
        ProcessOrderWorkflow processOrderWorkflow = workflowClient.newWorkflowStub(ProcessOrderWorkflow.class, ProcessOrderWorkflow.workflowOptions());
        WorkflowClient.start(processOrderWorkflow::processOrder, orderId, purchaseRequest);
    }
}
