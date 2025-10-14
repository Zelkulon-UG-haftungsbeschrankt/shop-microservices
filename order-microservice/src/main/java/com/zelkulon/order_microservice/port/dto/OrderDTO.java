/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/port/dto
 **/
package com.zelkulon.order_microservice.port.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
public class OrderDTO {
    private UUID orderId;
    private String username;
    private BigDecimal totalAmount;
    private String emaiL;
}
