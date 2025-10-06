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

import java.util.List;
import java.util.UUID;

//todo

public class CartService implements ICartService {
    private final ItemRepository itemRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    public CartService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private List<Item> getItemsByUsername(String username) {
        return itemRepository.findByUsername(username);
    }

    @Override
    public Cart getCartByUsername(String username) {
        return null;
    }

    @Override
    public Item getItmeById(UUID id) {
        return null;
    }

    @Override
    public Cart addItemToCart(Item item, String username) {
        return null;
    }

    @Override
    public void removeAllItems(String username) {

    }

    @Override
    public Cart deleteItemFromCart(Item item) throws ItemNotFoundException {
        return null;
    }
}
