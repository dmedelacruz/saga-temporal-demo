package com.dmedelacruz.storefront.api;

import com.dmedelacruz.storefront.purchase.service.OrderService;
import com.dmedelacruz.storemodel.RestResponse;
import com.dmedelacruz.storefront.purchase.workflow.PurchaseWorkflow;
import com.dmedelacruz.storemodel.storefront.PurchaseRequest;
import com.dmedelacruz.storemodel.storefront.PurchaseResponse;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.workflow.Workflow;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StorefrontController {

    private final OrderService orderService;
    private final WorkflowClient workflowClient;

    @PostMapping("/purchase")
    public ResponseEntity<RestResponse<PurchaseResponse>> purchase(@RequestBody PurchaseRequest purchaseRequest) {
        PurchaseWorkflow purchaseWorkflow = workflowClient.newWorkflowStub(PurchaseWorkflow.class, PurchaseWorkflow.workflowOptions());
//        WorkflowClient.start(purchaseWorkflow::processPurchase, purchaseRequest);
//        WorkflowClient.
//        return ResponseEntity.ok(Map.of("content", "initiated"));
        PurchaseResponse purchaseResponse = purchaseWorkflow.processPurchase(purchaseRequest);
        return ResponseEntity.ok(RestResponse.of(purchaseResponse));
    }

}
