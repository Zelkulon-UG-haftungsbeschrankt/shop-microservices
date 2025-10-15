/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/user/producer
 **/
package com.zelkulon.payment_microservice.port.product.user.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zelkulon.payment_microservice.core.domain.model.Payment;
import com.zelkulon.payment_microservice.port.product.dto.PaymentDTO;
import com.zelkulon.payment_microservice.port.product.dtomapper.PaymentDTOMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProducer.class);
    @Autowired
    private PaymentDTOMappingService paymentDTOMappingService;

    @Value("payment_exchange")
    private String exchange;

    @Value("")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    public PaymentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(exchange, "email_payment_confirmation", message);
    }

    public void sendMessageToEmailService(Payment payment) {
        PaymentDTO paymentDTO = paymentDTOMappingService.mapPaymentToPaymentDTO(payment);
        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message = objectMapper.writeValueAsString(paymentDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend(exchange, "email_payment_confirmation", message);
    }
}
