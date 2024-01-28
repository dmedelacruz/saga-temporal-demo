package com.dmedelacruz.storemodel.notification;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmailRequest {
    private String customerId;
    private String subject;
    private String message;
}
