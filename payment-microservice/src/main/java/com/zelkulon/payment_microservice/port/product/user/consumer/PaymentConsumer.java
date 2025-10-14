/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/user/consumer
 **/
package com.zelkulon.payment_microservice.port.product.user.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zelkulon.payment_microservice.core.domain.model.Payment;
import com.zelkulon.payment_microservice.core.domain.model.PaymentStatus;
import com.zelkulon.payment_microservice.core.domain.service.interfaces.IPaymentService;
import com.zelkulon.payment_microservice.port.product.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);
    private final IPaymentService paymentService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"orderToPayment"})
    public void consumeOrder(String message){
        ObjectMapper mapper = new ObjectMapper();
        OrderDTO orderDTO = null;
        try{
            orderDTO = objectMapper.readValue(message, OrderDTO.class);
            Payment payment = new Payment(orderDTO.getOrderId(),orderDTO.getUsername(),orderDTO.getTotalAmount(), PaymentStatus.PENDING,null);
            paymentService.createPayment(payment);
        }catch(JsonProcessingException e){
            LOGGER.error("Error processing message: {}",message,e);
            throw new RuntimeException(e);
        }catch(IllegalArgumentException e){
            LOGGER.error("Error creating payment for message: {}",message,e);
            throw new RuntimeException(e);
        }
    }
}
