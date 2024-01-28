package com.dmedelacruz.notificationsservice.api;

import com.dmedelacruz.notificationsservice.service.EmailService;
import com.dmedelacruz.storemodel.notification.NotificationEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<Boolean> emailUser(@RequestBody NotificationEmailRequest emailReqest) {
        Boolean emailed = emailService.emailUser(emailReqest);
        return ResponseEntity.ok(emailed);
    }

}
