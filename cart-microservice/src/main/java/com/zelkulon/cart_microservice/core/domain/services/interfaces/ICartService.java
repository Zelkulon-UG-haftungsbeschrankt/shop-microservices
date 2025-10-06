/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/core/domain/services/interfaces
 **/
package com.zelkulon.cart_microservice.core.domain.services.interfaces;

import com.zelkulon.cart_microservice.core.domain.model.Cart;
import com.zelkulon.cart_microservice.core.domain.model.Item;
import com.zelkulon.cart_microservice.core.domain.services.exception.ItemNotFoundException;


import java.util.UUID;

public interface ICartService {
    Cart getCartByUsername(String username);
    Item getItemById(UUID id);
    Cart addItemToCart(Item item, String username);
    void removeAllItems(String username);

    Cart deleteItemFromCart(UUID id) throws ItemNotFoundException;

}
