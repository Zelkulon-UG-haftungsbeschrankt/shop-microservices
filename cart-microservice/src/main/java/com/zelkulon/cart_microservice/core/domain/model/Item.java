/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/core/domain/model
 **/
package com.zelkulon.cart_microservice.core.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    UUID id;
    @Getter @Setter
    String username;
    @Getter
    UUID productId;
    @Getter @Setter
    String productName;
    @Getter @Setter
    BigDecimal productPrice;
    @Getter @Setter
    String imageLink;
}
