/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/core/domain/services/impl
 **/
package com.zelkulon.order_microservice.core.domain.services.impl;

import com.zelkulon.order_microservice.core.domain.model.Item;
import com.zelkulon.order_microservice.core.domain.services.interfaces.IItemRepository;
import com.zelkulon.order_microservice.core.domain.services.interfaces.IItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ItemService implements IItemService {
    private final IItemRepository itemRepository;

    public ItemService(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> addItemToOrder(List<Item> items) {
        List<Item> addedItems = new ArrayList<>();

        for (Item item : items) {
            Item savedItem = itemRepository.save(item);
            addedItems.add(savedItem);
        }

        return addedItems;
    }

    @Override
    public List<Item> getItemsForOrderId(UUID id) {
        return itemRepository.findByOrderId(id);
    }
}
