/**
 * created on 15.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/user/controller
 **/
package com.zelkulon.cart_microservice.port.user.controller;

import com.zelkulon.cart_microservice.port.producer.CartProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    CartProducer cartProducer;

    @GetMapping("/cart-queue")
    public void triggerCartQueue() {
        try {

            cartProducer.sendMessage("cart");
        }catch (Exception e){

        }
    }

    @GetMapping("/cart-queue1")
    @ResponseStatus(HttpStatus.OK)
    public String triggerCartQueue1() {
        return "cart-queue";
    }

    @GetMapping("/cart-queue2")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> triggerCartQueue2() {
        return ResponseEntity.ok("cart queue");
    }
}
