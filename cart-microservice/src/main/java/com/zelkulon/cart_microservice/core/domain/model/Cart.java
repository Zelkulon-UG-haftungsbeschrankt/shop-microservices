/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/core/domain/model
 **/
package com.zelkulon.cart_microservice.core.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {
    private List<Item> items;;
    private BigDecimal totalAmount;

    public Cart(List<Item> items, BigDecimal totalAmount) {
        this.items = items;
        this.totalAmount = totalAmount;
    }
}
