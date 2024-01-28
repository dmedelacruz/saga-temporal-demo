package com.dmedelacruz.storefront.purchase.activity;

import com.dmedelacruz.storefront.config.TemporalActivityApplicationContext;
import com.dmedelacruz.storefront.purchase.service.EmailService;
import com.dmedelacruz.storemodel.notification.NotificationEmailRequest;

public class NotificationActivityImpl implements NotificationActivity {

    private final EmailService emailService = TemporalActivityApplicationContext.getBean(EmailService.class);

    @Override
    public Boolean emailCustomer(String customerId, String subject, String message) {
        NotificationEmailRequest emailRequest = NotificationEmailRequest.builder().customerId(customerId).subject(subject).message(message).build();
        return emailService.emailCustomer(emailRequest);
    }
}
