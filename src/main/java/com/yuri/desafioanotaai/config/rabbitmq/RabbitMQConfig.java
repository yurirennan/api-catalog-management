package com.yuri.desafioanotaai.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbit.queue.name}")
    private String queue;

    @Value("${spring.rabbit.exchange.name}")
    private String exchange;

    @Value("${spring.rabbit.routing-key}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(queue, true, false, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange, true, false);
    }

    @Bean
    public Binding bindQueue() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }



}
