package com.dmedelacruz.storefront.external.restclient;

import com.dmedelacruz.storemodel.notification.NotificationEmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${restclient.notification.name}", url = "${restclient.notification.url}")
public interface NotificationRestClient {

    @PostMapping("/email")
    ResponseEntity<Boolean> emailUser(@RequestBody NotificationEmailRequest emailRequest);

}
