package com.yuri.desafioanotaai.consumers;

import com.yuri.desafioanotaai.services.CatalogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CatalogEmitConsumer {

    private final CatalogService catalogService;

    @Autowired
    public CatalogEmitConsumer(final CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RabbitListener(queues = {"${spring.rabbit.queue.name}"})
    public void listenCatalogEmitQueue(@Payload String owner) {
        this.catalogService.createCatalog(owner);
    }

}
