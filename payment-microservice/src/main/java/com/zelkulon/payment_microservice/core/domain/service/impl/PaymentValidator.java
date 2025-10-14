/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/core/domain/service/impl
 **/
package com.zelkulon.payment_microservice.core.domain.service.impl;

import com.zelkulon.payment_microservice.core.domain.model.Payment;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentWithOrderIdAlreadyExistsException;

import java.math.BigDecimal;

public class PaymentValidator {

    public static void validate(Payment payment) {
        if(payment == null){
            throw new IllegalArgumentException("Payment cannot be null");
        }

        if(payment.getOrderId() == null){
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        if(payment.getAmount() == null || payment.getAmount().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Amount has to be be positiv");
        }
    }


}
