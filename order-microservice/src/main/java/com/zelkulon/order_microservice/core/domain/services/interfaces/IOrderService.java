/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/services/interfaces
 **/
package com.zelkulon.order_microservice.core.domain.services.interfaces;

import com.zelkulon.order_microservice.core.domain.model.Order;
import com.zelkulon.order_microservice.core.domain.model.OrderRegistry;
import com.zelkulon.order_microservice.core.domain.services.exception.NotFoundByOrderIdException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IOrderService {
    Order createOrder(Order order, String username, Date date);
    List<OrderRegistry> getOrderRegistryByUsername(String username);
    Order getOrderByOrderId(UUID orderId) throws NotFoundByOrderIdException;
}
