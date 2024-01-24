package com.dmedelacruz.storefront.purchase.workflow;

import com.dmedelacruz.storefront.purchase.activity.InventoryActivity;
import com.dmedelacruz.storefront.purchase.activity.OrderActivity;
import com.dmedelacruz.storefront.purchase.activity.PaymentActivity;
import com.dmedelacruz.storefront.purchase.activity.PurchaseActivity;
import com.dmedelacruz.storemodel.inventory.ItemQtyDto;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;
import com.dmedelacruz.storemodel.order.OrderStatus;
import com.dmedelacruz.storemodel.order.UpdateOrderResponse;
import com.dmedelacruz.storemodel.payment.PaymentDetails;
import com.dmedelacruz.storemodel.payment.PaymentRequest;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;

public class ProcessOrderWorkflowImpl implements ProcessOrderWorkflow {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessOrderWorkflowImpl.class);

    private final Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
    private final Saga saga = new Saga(sagaOptions);

    private final OrderActivity orderActivity = Workflow.newActivityStub(OrderActivity.class, OrderActivity.orderActivityOptions);
    private final InventoryActivity inventoryActivity = Workflow.newActivityStub(InventoryActivity.class, InventoryActivity.inventoryActivityOptions);
    private final PaymentActivity paymentActivity = Workflow.newActivityStub(PaymentActivity.class, PaymentActivity.paymentActivityOptions);

    @Override
    public void processOrder(String orderId, PurchaseRequest purchaseRequest) {
        try {
            LOGGER.info("PROCESSING ORDER");
            Workflow.sleep(10000); //Just to check Order Status

            UpdateOrderResponse updateOrderResponse = orderActivity.updateOrderStatus(orderId, OrderStatus.PROCESSING);

            //Update Inventory
            UpdateInventoryRequest updateInventoryRequest = UpdateInventoryRequest.builder()
                    .items(purchaseRequest.getItems())
                    .build();
            UpdateInventoryResponse updateInventoryResponse = inventoryActivity.updateInventory(updateInventoryRequest);

            //Process Payment
            PaymentRequest paymentRequest = PaymentRequest.builder()
                    .paymentDetails(purchaseRequest.getPaymentDetails())
                    .paymentMethod(purchaseRequest.getPaymentMethod())
                    .build();
            Boolean paymentSuccess = paymentActivity.processPayment(paymentRequest);

            if(!paymentSuccess) {
                //TODO throw errror
            } else {
                //Update Order to Processed
                orderActivity.updateOrderStatus(orderId, OrderStatus.PROCESSED);
            }

            inventoryActivity.updateInventory(new UpdateInventoryRequest());
        } catch (Exception e) {
            e.printStackTrace();
            orderActivity.updateOrderStatus(orderId, OrderStatus.CANCELLED);
        }
    }
}
