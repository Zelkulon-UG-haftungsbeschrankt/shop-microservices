/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/dto
 **/
package com.zelkulon.payment_microservice.port.product.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemDTO {
    private UUID productId;
    private int quantity;
}
