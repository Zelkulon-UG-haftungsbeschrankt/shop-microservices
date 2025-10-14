/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/core/domain/service/interfaces
 **/
package com.zelkulon.payment_microservice.core.domain.service.interfaces;

import com.zelkulon.payment_microservice.core.domain.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IPaymentRepository extends CrudRepository<Payment, UUID> {
    List<Payment> findByOrderId(UUID orderId);
}
