/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/config
 **/
package com.zelkulon.cart_microservice.port.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("cartToProduct")
    private String productQueue;

    @Value("orderToCart")
    private String orderToCartQueue;

    @Value("cart_exchange")
    private String exchange;

    @Value("cart_toProduct")
    private String routingKey;

    @Bean
    public Queue queue(){
        return new Queue(productQueue);
    }

    @Bean
    public Queue orderToCartQueue(){
        return new Queue(orderToCartQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }
}
