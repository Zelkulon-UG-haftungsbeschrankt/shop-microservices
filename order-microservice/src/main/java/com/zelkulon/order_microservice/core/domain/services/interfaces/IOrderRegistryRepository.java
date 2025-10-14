/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/services/interfaces
 **/
package com.zelkulon.order_microservice.core.domain.services.interfaces;

import com.zelkulon.order_microservice.core.domain.model.OrderContact;
import com.zelkulon.order_microservice.core.domain.model.OrderRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IOrderRegistryRepository extends JpaRepository<OrderRegistry, UUID> {
    public List<OrderRegistry> findByUsername(String username);
    public OrderRegistry getByOrderId(UUID orderId);
}
