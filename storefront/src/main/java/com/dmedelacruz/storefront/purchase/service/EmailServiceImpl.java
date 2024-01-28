package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storefront.external.restclient.NotificationRestClient;
import com.dmedelacruz.storemodel.notification.EmailNotificationException;
import com.dmedelacruz.storemodel.notification.NotificationEmailRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final NotificationRestClient notificationRestClient;

    @Override
    public Boolean emailCustomer(NotificationEmailRequest emailRequest) {
        LOGGER.info("Sending Success Email to Customer");
        try {

            ResponseEntity<Boolean> response = notificationRestClient.emailUser(emailRequest);

            if(!response.getStatusCode().is2xxSuccessful()) {
                throw new EmailNotificationException();
            }

            LOGGER.info("Success Email Sent");
            return response.getBody().booleanValue();

        } catch (Exception e) {
            throw new EmailNotificationException();
        }
    }
}
