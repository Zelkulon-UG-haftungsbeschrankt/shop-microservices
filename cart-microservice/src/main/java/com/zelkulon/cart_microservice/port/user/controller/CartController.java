/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/user/controller
 **/
package com.zelkulon.cart_microservice.port.user.controller;

import com.zelkulon.cart_microservice.core.domain.model.Cart;
import com.zelkulon.cart_microservice.core.domain.model.Item;
import com.zelkulon.cart_microservice.core.domain.services.exception.ItemNotFoundException;
import com.zelkulon.cart_microservice.core.domain.services.interfaces.ICartService;
import com.zelkulon.cart_microservice.port.producer.CartProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private CartProducer cartProducer;

    @GetMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Cart getCart(@RequestParam String username){
        return cartService.getCartByUsername(username);
    }

    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Cart addItemToCart(@RequestBody Item item, @RequestParam String username){
        cartProducer.changeAmountOfProducts(item, -1);
        return cartService.addItemToCart(item,username);
    }

    @DeleteMapping("/cart/{idItem}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Cart removeItemFromCart(@PathVariable ("idItem") UUID idtem) throws ItemNotFoundException {
        Item item = cartService.getItmeById(idtem);
        cartProducer.changeAmountOfProducts(item, 1);
        Cart returnCart = cartService.deleteItemFromCart(item);
        return returnCart;
    }
}
