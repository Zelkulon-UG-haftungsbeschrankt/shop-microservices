/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/core/domain/services/impl
 **/
package com.zelkulon.cart_microservice.core.domain.services.impl;

import com.zelkulon.cart_microservice.core.domain.model.Cart;
import com.zelkulon.cart_microservice.core.domain.model.Item;
import com.zelkulon.cart_microservice.core.domain.services.exception.ItemNotFoundException;
import com.zelkulon.cart_microservice.core.domain.services.interfaces.ICartService;
import com.zelkulon.cart_microservice.core.domain.services.interfaces.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

//todo

public class CartService implements ICartService {
    private final ItemRepository itemRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    CartService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private List<Item> getItemsByUsername(String username) {
        return itemRepository.findByUsername(username);
    }

    private BigDecimal getTotalAmountOfItemList(List<Item> items) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (int i = 0; i < items.size(); i++) {
            totalAmount = totalAmount.add(items.get(i).getProductPrice());
        }
        return totalAmount;
    }


    @Override
    public Cart getCartByUsername(String username) {
        List<Item> items = getItemsByUsername(username);
        BigDecimal totalAmount = this.getTotalAmountOfItemList(items);
        return new Cart(items,totalAmount);
    }

    @Override
    public Item getItemById(UUID id) {
        return itemRepository.getReferenceById(id); // keine deprecated getById
    }

    @Override
    public Cart addItemToCart(Item item, String username) {
        item.setUsername(username);
        itemRepository.save(item);
        return getCartByUsername(username);
    }

    @Override
    public void removeAllItems(String username) {
        List<Item> itemsList = itemRepository.findByUsername(username);
        for (int i = 0; i < itemsList.size(); i++) {
            itemRepository.deleteById(itemsList.get(i).getId());
        }
    }

    @Override
    public Cart deleteItemFromCart(UUID id) throws ItemNotFoundException {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException());
        itemRepository.deleteById(id);
        return getCartByUsername(item.getUsername());
    }
}
