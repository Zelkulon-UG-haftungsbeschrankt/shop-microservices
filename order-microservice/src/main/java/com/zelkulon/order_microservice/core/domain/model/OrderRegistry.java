/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/model
 **/
package com.zelkulon.order_microservice.core.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
public class OrderRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID orderId;
    @Setter
    @Getter
    private String username;
    @Getter
    private Date date;
    @Getter
    private BigDecimal totalAmount;

    public OrderRegistry() {
    }

    public OrderRegistry(String username, Date date, BigDecimal totalAmount) {
        this.username = username;
        this.date = date;
        this.totalAmount = totalAmount;
    }
}
