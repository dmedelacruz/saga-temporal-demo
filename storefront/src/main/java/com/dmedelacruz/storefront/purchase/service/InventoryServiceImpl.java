package com.dmedelacruz.storefront.purchase.service;

import com.dmedelacruz.storefront.external.restclient.InventoryRestClient;
import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storemodel.inventory.InventoryProcessingException;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryRequest;
import com.dmedelacruz.storemodel.inventory.UpdateInventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRestClient inventoryRestClient;

    @Override
    public UpdateInventoryResponse updateInventoryForPurchase(UpdateInventoryRequest updateInventoryRequest) {
        try {
            ResponseEntity<RestResponse<UpdateInventoryResponse>> response = inventoryRestClient.updateItemsForPurchase(updateInventoryRequest);
            if(response.getStatusCode().is2xxSuccessful()) {
                UpdateInventoryResponse content = response.getBody().getContent();
                return content;
            } else {
                throw new InventoryProcessingException();
            }
        } catch (Exception e) {
            throw new InventoryProcessingException("Item Out of Stock");
        }
    }

    @Override
    public UpdateInventoryResponse reverseInventoryForPurchase(UpdateInventoryRequest updateInventoryRequest) {
        try {
            ResponseEntity<RestResponse<UpdateInventoryResponse>> response = inventoryRestClient.reverseItemsPurchase(updateInventoryRequest);
            if(response.getStatusCode().is2xxSuccessful()) {
                UpdateInventoryResponse content = response.getBody().getContent();
                return content;
            } else {
                throw new InventoryProcessingException();
            }
        } catch (Exception e) {
            throw new InventoryProcessingException();
        }
    }
}
