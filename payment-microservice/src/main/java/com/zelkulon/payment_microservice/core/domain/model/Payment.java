/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/core/domain/model
 **/
package com.zelkulon.payment_microservice.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID orderId;

    private String username;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    public Payment(UUID orderId, String username, BigDecimal amount, PaymentStatus status, PaymentMethod method) {
        this.orderId = orderId;
        this.username = username;
        this.amount = amount;
        this.status = status;
        this.method = method;
    }
}
