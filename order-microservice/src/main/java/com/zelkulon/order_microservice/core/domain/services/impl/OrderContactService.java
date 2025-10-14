/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/services/impl
 **/
package com.zelkulon.order_microservice.core.domain.services.impl;

import com.zelkulon.order_microservice.core.domain.model.OrderContact;
import com.zelkulon.order_microservice.core.domain.services.exception.NotFoundByOrderIdException;
import com.zelkulon.order_microservice.core.domain.services.interfaces.IOrderContactRepository;
import com.zelkulon.order_microservice.core.domain.services.interfaces.IOrderContactService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderContactService implements IOrderContactService{
    private final IOrderContactRepository orderContactRepository;

    public OrderContactService(IOrderContactRepository orderContactRepository) {
        this.orderContactRepository = orderContactRepository;
    }

    @Override
    public OrderContact getOrderContactByOrderId(UUID orderId) throws NotFoundByOrderIdException {

        List<OrderContact> orderContacts = orderContactRepository.findByOrderId(orderId);

        if (orderContacts.isEmpty()) {
            throw new NotFoundByOrderIdException();
        }

        return orderContacts.get(0);
    }

    @Override
    public OrderContact createOrderContact(OrderContact orderContact) {
        return orderContactRepository.save(orderContact);
    }
}
