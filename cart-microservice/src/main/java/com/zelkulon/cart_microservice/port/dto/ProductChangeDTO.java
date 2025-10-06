/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/dto
 **/
package com.zelkulon.cart_microservice.port.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class ProductChangeDTO {
    UUID id;
    int changeAmount;
}
