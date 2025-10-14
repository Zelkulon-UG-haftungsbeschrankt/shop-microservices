/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/dto
 **/
package com.zelkulon.payment_microservice.port.product.dto;

import com.zelkulon.payment_microservice.core.domain.model.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentDTO {
    private UUID paymentId;
    private String username;
    private BigDecimal amount;
    private UUID orderId;
    private PaymentStatus status;
}
