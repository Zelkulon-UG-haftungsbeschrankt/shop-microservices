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

import java.util.UUID;

@Entity
public class OrderContact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Getter @Setter
    private UUID orderId;
    @Getter
    private Gender gender;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private String street;
    @Getter
    private String houseNumber;
    @Getter
    private String postalCode;
    @Getter
    private String city;
    @Getter
    private String email;
}
