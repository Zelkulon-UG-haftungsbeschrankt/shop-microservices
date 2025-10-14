/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/config
 **/
package com.zelkulon.payment_microservice.port.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("paymentToEmail")
    private String paymentToEmailQueue;
    @Value("orderToPayment")
    private String orderToPaymentQueue;

    @Value("payment_exchange")
    private String exchange;

    @Value("email_payment_confirmation")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(paymentToEmailQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(Queue queue){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }
}
