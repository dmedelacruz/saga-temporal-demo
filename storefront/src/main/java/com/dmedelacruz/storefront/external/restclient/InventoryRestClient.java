package com.dmedelacruz.storefront.external.restclient;

import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${restclient.inventory.name}", url = "${restclient.inventory.url}")
public interface InventoryRestClient {

    @PutMapping("/purchase")
    ResponseEntity<RestResponse<UpdateInventoryResponse>> updateItemsForPurchase(@RequestBody UpdateInventoryRequest request);

    @PutMapping("/reverse-purchase")
    ResponseEntity<RestResponse<UpdateInventoryResponse>> reverseItemsPurchase(@RequestBody UpdateInventoryRequest request);
}
