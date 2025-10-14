/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/services/interfaces
 **/
package com.zelkulon.order_microservice.core.domain.services.interfaces;

import com.zelkulon.order_microservice.core.domain.model.OrderContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IOrderContactRepository extends JpaRepository<OrderContact, UUID> {
    List<OrderContact> findByOrderId(UUID orderId);
}
