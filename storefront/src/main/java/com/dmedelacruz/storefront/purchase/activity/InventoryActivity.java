package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;

import java.time.Duration;

@ActivityInterface
public interface InventoryActivity {

    @ActivityMethod
    UpdateInventoryResponse updateInventory(UpdateInventoryRequest updateInventoryRequest);

    RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofMinutes(1))
            .setMaximumInterval(Duration.ofMinutes(10))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(10)
//            .setDoNotRetry(Exception.class.getName()) TODO indicate exceptions that you dont want to retry
            .build();

    ActivityOptions inventoryActivityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofMinutes(10))
            .setRetryOptions(retryOptions)
            .build();
}
