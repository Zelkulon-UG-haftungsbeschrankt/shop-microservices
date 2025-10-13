/**
 * created on 13.10.25
 * by hdi10 with IntelliJ IDEA
 * for email-microservice
 * path to this file is: src/main/java/com/zelkulon/email_microservice/core/service/interfaces
 **/
package com.zelkulon.email_microservice.core.service.interfaces;

import com.zelkulon.email_microservice.core.model.OrderEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IOrderEmailRepository extends JpaRepository<OrderEmail, UUID> {
    List<OrderEmail> findByOrderId(UUID orderId);
}
