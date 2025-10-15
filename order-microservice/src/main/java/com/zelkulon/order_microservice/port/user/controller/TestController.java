/**
 * created on 15.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/port/user/controller
 **/
package com.zelkulon.order_microservice.port.user.controller;

import com.zelkulon.order_microservice.core.domain.services.impl.OrderService;
import com.zelkulon.order_microservice.port.rabbitProducer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private OrderProducer orderProducer;

    @GetMapping("/order-queue")
    public void triggerOrderQueue() {
        try {
            orderProducer.sendMessage("order");
        }catch (Exception e){

        }
    }

    @GetMapping("/order-queue1")
    @ResponseStatus(HttpStatus.OK)
    public String triggerOrderQueue1() {
        return "order-queue";
    }

    @GetMapping("/order-queue2")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> triggerOrderQueue2() {
        return ResponseEntity.ok("order queue");
    }
}
