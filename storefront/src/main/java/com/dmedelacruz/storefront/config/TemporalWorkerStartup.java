package com.dmedelacruz.storefront.config;

import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemporalWorkerStartup {

    private final WorkerFactory workerFactory;

    @EventListener(ApplicationReadyEvent.class)
    public void startWorkers() {
        workerFactory.start();
    }


}