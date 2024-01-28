package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storemodel.order.*;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;

import java.time.Duration;

@ActivityInterface
public interface NotificationActivity {

    @ActivityMethod
    Boolean emailCustomer(String customerId, String subject, String message);

    RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(3))
//            .setBackoffCoefficient(2)
            .setMaximumAttempts(3)
            .build();

    ActivityOptions notificationrActivityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofMinutes(10))
            .setRetryOptions(retryOptions)
            .build();

}
