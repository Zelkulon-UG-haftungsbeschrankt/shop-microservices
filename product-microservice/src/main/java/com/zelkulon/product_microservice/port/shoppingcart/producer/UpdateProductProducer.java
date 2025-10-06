/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for product-microservice
 * path to this file is: src/main/java/com/zelkulon/product_microservice/port/shoppingcart/producer
 **/
package com.zelkulon.product_microservice.port.shoppingcart.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductProducer {

    @Value("product_exchange")
    private String exchange;

    @Value("item_routing_key")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProductProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public UpdateProductProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info("Sending message : {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
