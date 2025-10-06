/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/consumer
 **/
package com.zelkulon.cart_microservice.port.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zelkulon.cart_microservice.core.domain.services.interfaces.ICartService;
import com.zelkulon.cart_microservice.port.dto.OrderDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private ICartService cartService;

    @RabbitListener(queues = {"orderToCart"})
    public void consume(String message){
        OrderDTO orderDTO = null;
        try{
            orderDTO = objectMapper.readValue(message, OrderDTO.class);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
        cartService.removeAllItems(orderDTO.getUsername());
    }


}
