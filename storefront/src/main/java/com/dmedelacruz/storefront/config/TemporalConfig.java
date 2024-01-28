package com.dmedelacruz.storefront.config;

import com.dmedelacruz.storefront.purchase.activity.*;
import com.dmedelacruz.storefront.purchase.workflow.ProcessOrderWorkflow;
import com.dmedelacruz.storefront.purchase.workflow.ProcessOrderWorkflowImpl;
import com.dmedelacruz.storefront.purchase.workflow.PurchaseWorkflow;
import com.dmedelacruz.storefront.purchase.workflow.PurchaseWorkflowImpl;
import com.google.protobuf.util.Durations;
import io.temporal.api.workflowservice.v1.ListNamespacesRequest;
import io.temporal.api.workflowservice.v1.RegisterNamespaceRequest;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class TemporalConfig {

//    @Value("${temporal.endpoint}")
//    private String TARGET_ENDPOINT;

    @Value("${temporal.namespace}")
    private String NAMESPACE;

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporalConfig.class);

    @Bean
    public WorkflowServiceStubs getWorkflowStubs(){
//        return WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder().setTarget(TARGET_ENDPOINT).build());
        return WorkflowServiceStubs.newLocalServiceStubs(); //FOR LOCAL TEMPORAL INSTANCE ONLY
    }

    @Bean
    public WorkflowClient getWorkflowClient(WorkflowServiceStubs workflowServiceStubs){
        List<String> namespaceList = workflowServiceStubs.blockingStub()
                .listNamespaces(ListNamespacesRequest.newBuilder().build())
                .getNamespacesList().stream()
                .map(describeNamespaceResponse -> describeNamespaceResponse.getNamespaceInfo().getName())
                .toList();

        if(!namespaceList.contains(NAMESPACE)){
            LOGGER.info("namespace not exist: " + NAMESPACE);
            //creation of namespace does not reflect on real time, has delay of about 5secs.,
            //will throw statusRuntimeException until Poller see the created namespace
            RegisterNamespaceRequest request = RegisterNamespaceRequest.newBuilder()
                    .setNamespace(NAMESPACE)
                    .setWorkflowExecutionRetentionPeriod(Durations.fromDays(3)) // keeps the Workflow Execution
                    //Event History for up to 3 days in the Persistence store. Not setting this value will throw an error.
                    .build();
            workflowServiceStubs.blockingStub().registerNamespace(request);
            LOGGER.info("namespace created: " + NAMESPACE);
        }
        return WorkflowClient.newInstance(workflowServiceStubs, WorkflowClientOptions.newBuilder().setNamespace(NAMESPACE).build());
    }

    @Bean(name="createWorkerFactory")
    public WorkerFactory createWorkerFactory(WorkflowClient workflowClient){
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public Worker purchaseWorkflowWorker(WorkerFactory workerFactory) {
        Worker worker = workerFactory.newWorker(PurchaseWorkflow.TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(PurchaseWorkflowImpl.class);
        worker.registerActivitiesImplementations(new OrderActivityImpl(), new InventoryActivityImpl(), new PurchaseActivityImpl());
        return worker;
    }

    @Bean
    public Worker processOrderWorkflow(WorkerFactory workerFactory) {
        Worker worker = workerFactory.newWorker(ProcessOrderWorkflow.TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(ProcessOrderWorkflowImpl.class);
        worker.registerActivitiesImplementations(new OrderActivityImpl(), new InventoryActivityImpl(), new PurchaseActivityImpl(), new PaymentActivityImpl(), new NotificationActivityImpl());
        return worker;
    }

}
