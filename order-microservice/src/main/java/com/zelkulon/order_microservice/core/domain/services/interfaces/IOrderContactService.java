/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/services/interfaces
 **/
package com.zelkulon.order_microservice.core.domain.services.interfaces;

import com.zelkulon.order_microservice.core.domain.model.OrderContact;
import com.zelkulon.order_microservice.core.domain.services.exception.NotFoundByOrderIdException;

import java.util.UUID;

public interface IOrderContactService {
    public OrderContact getOrderContactByOrderId(UUID orderId) throws NotFoundByOrderIdException;
    public OrderContact createOrderContact(OrderContact orderContact);
}
