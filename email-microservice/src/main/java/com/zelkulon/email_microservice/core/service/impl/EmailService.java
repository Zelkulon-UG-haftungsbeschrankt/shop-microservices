/**
 * created on 13.10.25
 * by hdi10 with IntelliJ IDEA
 * for email-microservice
 * path to this file is: src/main/java/com/zelkulon/email_microservice/core/service/impl
 **/
package com.zelkulon.email_microservice.core.service.impl;

import com.zelkulon.email_microservice.core.model.Email;
import com.zelkulon.email_microservice.core.model.OrderEmail;
import com.zelkulon.email_microservice.core.service.interfaces.IEmailService;
import com.zelkulon.email_microservice.core.service.interfaces.IOrderEmailRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class EmailService implements IEmailService {

    private IOrderEmailRepository orderEmailRepository;

    public EmailService(IOrderEmailRepository orderEmailRepository) {
        this.orderEmailRepository = orderEmailRepository;
    }

    @Override
    public Email generatePaymentConfirmationMail(BigDecimal amount, UUID orderNr) {
        return null;
    }

    @Override
    public Email generateOrderConfirmationMail(BigDecimal amount, OrderEmail orderEmail) {
        return null;
    }

    @Override
    public OrderEmail creatOrderMail(String username, String email, UUID orderNr) {
        return null;
    }

    @Override
    public OrderEmail getOrderEmailByOrderId(UUID orderId) {
        return null;
    }
}
