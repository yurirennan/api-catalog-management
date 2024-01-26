package com.yuri.desafioanotaai.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQOwnerProducerService {

    @Value("${spring.rabbit.exchange.name}")
    private String exchange;

    @Value("${spring.rabbit.routing-key}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQOwnerProducerService(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void emitCatalog(final String message) {
        this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
