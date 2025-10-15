/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/producer
 **/
package com.zelkulon.cart_microservice.port.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zelkulon.cart_microservice.core.domain.model.Item;
import com.zelkulon.cart_microservice.port.dto.ProductChangeDTO;
import com.zelkulon.cart_microservice.port.dtomapper.ItemToProductDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartProducer.class);
    @Autowired
    private ItemToProductDTOMapper mapper;

    @Value("cart_exchange")
    private String exchange;

    private final RabbitTemplate rabbitTemplate;

    public CartProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(exchange, "cart_ToProduct", message);
    }

    public void changeAmountOfProducts(Item item, int diffenrence){
        ProductChangeDTO productChangeDTO = mapper.getProductChangeDTO(item,diffenrence);
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(productChangeDTO);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

        rabbitTemplate.convertAndSend(exchange,"cart_ToProduct",message);
    }
}
