/**
 * created on 13.10.25
 * by hdi10 with IntelliJ IDEA
 * for email-microservice
 * path to this file is: src/main/java/com/zelkulon/email_microservice/core/service/interfaces
 **/
package com.zelkulon.email_microservice.core.service.interfaces;

import com.zelkulon.email_microservice.core.model.Email;
import com.zelkulon.email_microservice.core.model.OrderEmail;

import java.math.BigDecimal;
import java.util.UUID;

public interface IEmailService {

    Email generatePaymentConfirmationMail(BigDecimal amount, UUID orderNr);

    Email generateOrderConfirmationMail(BigDecimal amount, OrderEmail orderEmail);

    OrderEmail creatOrderMail(String username, String email, UUID orderNr);

    OrderEmail getOrderEmailByOrderId(UUID orderId);
}
