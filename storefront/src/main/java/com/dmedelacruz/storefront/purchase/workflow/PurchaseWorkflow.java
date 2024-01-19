package com.dmedelacruz.storefront.purchase.workflow;

import com.dmedelacruz.storefront.purchase.dto.PurchaseRequest;
import io.temporal.client.WorkflowOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;

@WorkflowInterface
public interface PurchaseWorkflow {

    final String TASK_QUEUE = PurchaseWorkflow.class.getSimpleName() + "_queue";

    @WorkflowMethod
    void processPurchase(PurchaseRequest purchaseRequest);

    static RetryOptions retryOptions() {
        return RetryOptions.newBuilder()
                .setMaximumAttempts(1)
                .build();
    }

    static WorkflowOptions workflowOptions() {
        return WorkflowOptions.newBuilder()
//                .setSearchAttributes(WorkflowSearchAttributeUtil.initializeSearchAttributes())
                .setWorkflowId(UUID.randomUUID().toString())
                .setTaskQueue(TASK_QUEUE)
                .setRetryOptions(retryOptions())
                .build();
    }
}