/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/model
 **/
package com.zelkulon.order_microservice.core.domain.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    @Getter
    UUID orderId;
    @Getter
    private OrderContact orderContact;
    @Getter
    private List<Item> items;
    @Getter
    private OrderRegistry orderRegistry;
    @Getter
    private BigDecimal totalAmount;

    public Order(OrderContact orderContact, List<Item> items, OrderRegistry orderRegistry, BigDecimal totalAmount) {
        this.orderId = orderContact.getOrderId();
        this.orderContact = orderContact;
        this.items = items;
        this.orderRegistry = orderRegistry;
        this.totalAmount = totalAmount;
    }

    public Order() {
    }
}
