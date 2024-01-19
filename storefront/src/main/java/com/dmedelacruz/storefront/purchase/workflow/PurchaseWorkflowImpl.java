package com.dmedelacruz.storefront.purchase.workflow;

import com.dmedelacruz.storefront.purchase.activity.OrderActivity;
import com.dmedelacruz.storefront.purchase.activity.PurchaseActivity;
import com.dmedelacruz.storemodel.order.CreateOrderResponse;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;
import com.dmedelacruz.storemodel.storefront.PurchaseResponse;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;

public class PurchaseWorkflowImpl implements PurchaseWorkflow {

    private final Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
    private final Saga saga = new Saga(sagaOptions);

    private final OrderActivity orderActivity = Workflow.newActivityStub(OrderActivity.class, OrderActivity.orderActivityOptions);
    private final PurchaseActivity purchaseActivity = Workflow.newActivityStub(PurchaseActivity.class, PurchaseActivity.purchaseActivityOptions);


    @Override
    public PurchaseResponse processPurchase(PurchaseRequest purchaseRequest) {

        String workflowId = Workflow.getInfo().getWorkflowId();

        try {
            CreateOrderResponse createdOrder = orderActivity.createOrder(purchaseRequest);

            //TODO persist Order PurchaseTable
            purchaseActivity.processOrder(createdOrder.getOrderId(), purchaseRequest);

            return PurchaseResponse.builder()
                    .customerId(createdOrder.getCustomerId())
                    .orderId(createdOrder.getOrderId())
                    .workflowId(workflowId)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
