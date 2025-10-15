/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/port/rabbitProducer
 **/
package com.zelkulon.order_microservice.port.rabbitProducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zelkulon.order_microservice.core.domain.model.Order;
import com.zelkulon.order_microservice.port.dto.OrderDTO;
import com.zelkulon.order_microservice.port.dtoMapper.OrderDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    @Value("order_exchange")
    private String exchange;
    @Autowired
    private OrderDTOMapper orderDTOMapper;

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(exchange, "*", message);
    }

    public void sendToAll(Order order){
        OrderDTO orderDTO = orderDTOMapper.getMapperOrderDTO(order);
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(orderDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend(exchange, "*", message);
    }
}
