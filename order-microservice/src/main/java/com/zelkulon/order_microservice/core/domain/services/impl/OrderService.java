/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/services/impl
 **/
package com.zelkulon.order_microservice.core.domain.services.impl;

import com.zelkulon.order_microservice.core.domain.model.Item;
import com.zelkulon.order_microservice.core.domain.model.Order;
import com.zelkulon.order_microservice.core.domain.model.OrderContact;
import com.zelkulon.order_microservice.core.domain.model.OrderRegistry;
import com.zelkulon.order_microservice.core.domain.services.exception.NotFoundByOrderIdException;
import com.zelkulon.order_microservice.core.domain.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class OrderService implements IOrderService {
    @Autowired
    private final IOrderRegistryRepository orderRegistryRepository;
    @Autowired
    private final IOrderContactService oderContactService;
    @Autowired
    private final IItemService itemService;
    @Autowired
    private IOrderContactService iOrderContactService;

    public OrderService(IOrderContactRepository orderRepository,IItemRepository itemRepository, IOrderRegistryRepository orderRegistryRepository, IOrderContactService oderContactService, IItemService itemService) {
        this.orderRegistryRepository = orderRegistryRepository;
        this.oderContactService = oderContactService;
        this.itemService = itemService;
    }

    @Override
    public Order createOrder(Order order, String username, Date date) {
        OrderRegistry orderRegistry = orderRegistryRepository.save(new OrderRegistry(username,date,order.getTotalAmount()));
        UUID orderId = orderRegistry.getOrderId();
        OrderContact orderContact = order.getOrderContact();
        orderContact.setOrderId(orderId);

        List<Item> items = order.getItems();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            item.setOrderId(orderId);
        }

        orderRegistryRepository.save(orderRegistry);
        oderContactService.createOrderContact(orderContact);
        itemService.addItemToOrder(items);

        return new Order(orderContact,items,orderRegistry,order.getTotalAmount());
    }

    @Override
    public List<OrderRegistry> getOrderRegistryByUsername(String username) {
        return orderRegistryRepository.findByUsername(username);
    }

    @Override
    public Order getOrderByOrderId(UUID orderId) throws NotFoundByOrderIdException {
        OrderContact contact = iOrderContactService.getOrderContactByOrderId(orderId);
        List<Item> items = itemService.getItemsForOrderId(orderId);
        OrderRegistry orderRegistry = orderRegistryRepository.getByOrderId(orderId);

        return new Order(contact,items,orderRegistry,orderRegistry.getTotalAmount());
    }
}
