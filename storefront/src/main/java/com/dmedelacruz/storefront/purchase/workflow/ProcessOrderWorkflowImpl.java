package com.dmedelacruz.storefront.purchase.workflow;

import com.dmedelacruz.storefront.purchase.activity.*;
import com.dmedelacruz.storemodel.inventory.ItemQtyDto;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;
import com.dmedelacruz.storemodel.order.OrderStatus;
import com.dmedelacruz.storemodel.order.UpdateOrderResponse;
import com.dmedelacruz.storemodel.payment.PaymentDetails;
import com.dmedelacruz.storemodel.payment.PaymentRequest;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;
import io.temporal.failure.ActivityFailure;
import io.temporal.failure.ApplicationFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;

public class ProcessOrderWorkflowImpl implements ProcessOrderWorkflow {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessOrderWorkflowImpl.class);

//    private final Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
private final Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(false).build();
    private final Saga saga = new Saga(sagaOptions);

    private final OrderActivity orderActivity = Workflow.newActivityStub(OrderActivity.class, OrderActivity.orderActivityOptions);
    private final InventoryActivity inventoryActivity = Workflow.newActivityStub(InventoryActivity.class, InventoryActivity.inventoryActivityOptions);
    private final PaymentActivity paymentActivity = Workflow.newActivityStub(PaymentActivity.class, PaymentActivity.paymentActivityOptions);

    private final NotificationActivity notificationActivity = Workflow.newActivityStub(NotificationActivity.class, NotificationActivity.notificationrActivityOptions);
    @Override
    public void processOrder(String orderId, PurchaseRequest purchaseRequest) {
        try {

//            saga.addCompensation(() -> notificationActivity.emailCustomer(purchaseRequest.getCustomerId(), "Order: " + orderId + " Cancelled", ""));
            LOGGER.info("PROCESSING ORDER");
//            Workflow.sleep(10000);

            UpdateOrderResponse updateOrderResponse = orderActivity.updateOrderStatus(orderId, OrderStatus.PROCESSING);
            saga.addCompensation(() -> orderActivity.cancelOrder(orderId));

//            Workflow.sleep(10000);
            //Update Inventory
            UpdateInventoryRequest updateInventoryRequest = UpdateInventoryRequest.builder()
                    .items(purchaseRequest.getItems())
                    .build();
            UpdateInventoryResponse updateInventoryResponse = inventoryActivity.updateInventory(updateInventoryRequest);
            saga.addCompensation(() -> inventoryActivity.updateInventoryReversePurchase(updateInventoryRequest));

//            Workflow.sleep(10000);
            //Process Payment
            PaymentDetails paymentDetails = purchaseRequest.getPaymentDetails();
            paymentDetails.setCustomerId(purchaseRequest.getCustomerId());
            paymentDetails.setOrderId(orderId);
            PaymentRequest paymentRequest = PaymentRequest.builder()
                    .paymentDetails(paymentDetails)
                    .paymentMethod(purchaseRequest.getPaymentMethod())
                    .build();
            Boolean paymentSuccess = paymentActivity.processPayment(paymentRequest);

//            Workflow.sleep(10000);
            orderActivity.updateOrderStatus(orderId, OrderStatus.PROCESSED);

//            Workflow.sleep(10000);
            notificationActivity.emailCustomer(purchaseRequest.getCustomerId(), "Order: " + orderId + " Processed", "Processed Successfully");

        } catch (ActivityFailure activityFailure) {
            if(!activityFailure.getActivityType().equals("EmailCustomer")) {
                saga.compensate();
                ApplicationFailure cause = (ApplicationFailure) activityFailure.getCause();
                notificationActivity.emailCustomer(purchaseRequest.getCustomerId(), "Order: " + orderId + " Cancelled", cause.getOriginalMessage());
            }
        }
    }
}
