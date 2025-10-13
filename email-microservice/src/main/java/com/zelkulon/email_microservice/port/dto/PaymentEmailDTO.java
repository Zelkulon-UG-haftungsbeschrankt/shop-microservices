/**
 * created on 13.10.25
 * by hdi10 with IntelliJ IDEA
 * for email-microservice
 * path to this file is: src/main/java/com/zelkulon/email_microservice/port/dto
 **/
package com.zelkulon.email_microservice.port.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentEmailDTO {

    String username;
    BigDecimal amount;
    UUID orderId;


}
