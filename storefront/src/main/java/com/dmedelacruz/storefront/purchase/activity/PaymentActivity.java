package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storemodel.payment.PaymentRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;

import java.time.Duration;

@ActivityInterface
public interface PaymentActivity {

    @ActivityMethod
    Boolean processPayment(PaymentRequest paymentRequest);

    RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(5))
            .setMaximumInterval(Duration.ofMinutes(1))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(10)
//            .setDoNotRetry(Exception.class.getName()) TODO indicate exceptions that you dont want to retry
            .build();

    ActivityOptions paymentActivityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofMinutes(10))
            .setRetryOptions(retryOptions)
            .build();

}
