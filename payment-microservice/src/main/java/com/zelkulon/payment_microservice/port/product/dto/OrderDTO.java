/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/dto
 **/
package com.zelkulon.payment_microservice.port.product.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private UUID orderId;

    private String username;
    private BigDecimal totalAmount;
    private String email;
    private List<OrderItemDTO> items;
}
