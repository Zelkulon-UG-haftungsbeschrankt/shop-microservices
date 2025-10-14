/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/services/interfaces
 **/
package com.zelkulon.order_microservice.core.domain.services.interfaces;

import com.zelkulon.order_microservice.core.domain.model.Item;

import java.util.List;
import java.util.UUID;

public interface IItemService {
    public List<Item> addItemToOrder(List<Item> items);

    public List<Item> getItemsForOrderId(UUID id);
}
