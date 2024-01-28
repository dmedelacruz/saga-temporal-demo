package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storemodel.notification.NotificationEmailRequest;

public interface EmailService {

    Boolean emailCustomer(NotificationEmailRequest emailRequest);

}
