/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for product-microservice
 * path to this file is: src/main/java/com/zelkulon/product_microservice/port/admin/consumer
 **/
package com.zelkulon.product_microservice.port.admin.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PriceChangeConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceChangeConsumer.class);

    @RabbitListener(queues = {"item"})
    public void consume(String message) {
        LOGGER.info(String.format("Received Message : %s", message ));
        // TODO: Business Logic
    }
}
