/**
 * created on 13.10.25
 * by hdi10 with IntelliJ IDEA
 * for email-microservice
 * path to this file is: src/main/java/com/zelkulon/email_microservice/core/model
 **/
package com.zelkulon.email_microservice.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Entity
public class OrderEmail {
    public OrderEmail() {
    }

    public OrderEmail(UUID id, String emailAdress, String username) {
        this.id = id;
        this.emailAdress = emailAdress;
        this.username = username;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Getter
    @NotNull
    private String emailAdress;
    @Getter
    @NotNull
    private String username;
}
