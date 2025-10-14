/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/port/rabbitConsumer
 **/
package com.zelkulon.order_microservice.port.rabbitConsumer;

import com.zelkulon.order_microservice.port.dto.OrderDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    @RabbitListener(queues = {"self"})
    public void consume(String message) {

    }
}
