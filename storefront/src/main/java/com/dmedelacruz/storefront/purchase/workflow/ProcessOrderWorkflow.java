package com.dmedelacruz.storefront.purchase.workflow;

import com.dmedelacruz.storemodel.storefront.PurchaseRequest;
import io.temporal.client.WorkflowOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.time.Duration;
import java.util.UUID;

@WorkflowInterface
public interface ProcessOrderWorkflow {

    String TASK_QUEUE = ProcessOrderWorkflow.class.getSimpleName() + "_queue";

    @WorkflowMethod
    void processOrder(String orderId, PurchaseRequest purchaseRequest);

    static RetryOptions retryOptions() {
        return RetryOptions.newBuilder()
                .setInitialInterval(Duration.ofSeconds(20))
                .setMaximumAttempts(3)
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
