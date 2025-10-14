/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/consumer
 **/
package com.zelkulon.cart_microservice.port.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartConsumer {

    @Value("orderToCart")
    private String exchange;

    @Value("cart_toProduct")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartConsumer.class);

    private final RabbitTemplate rabbitTemplate;

    public CartConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener
    public void sendMessage(String message) {
        LOGGER.info("Sending message : {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

//    private ObjectMapper mapper = new ObjectMapper();
//    @Autowired
//    ICartService cartService;
//
//    @RabbitListener(queues = {"orderToCart"})
//    public void consume(String message){
//        OrderDTO orderDTO = null;
//        try{
//            orderDTO = mapper.readValue(message, OrderDTO.class);
//        }catch(JsonProcessingException e){
//            throw new RuntimeException(e);
//        }
//        cartService.removeAllItems(orderDTO.getUsername());
//    }


}
