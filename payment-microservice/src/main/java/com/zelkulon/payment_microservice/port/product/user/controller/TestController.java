/**
 * created on 15.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/user/controller
 **/
package com.zelkulon.payment_microservice.port.product.user.controller;

import com.zelkulon.payment_microservice.port.product.user.producer.PaymentProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    PaymentProducer paymentProducer;

    @GetMapping("/payment-queue")
    public void triggerPaymentQueue() {
        try {

            paymentProducer.sendMessage("payment");
        }catch (Exception e){

        }
    }

    @GetMapping("/payment-queue1")
    @ResponseStatus(HttpStatus.OK)
    public String triggerPaymentQueue1() {
        return "payment-queue";
    }

    @GetMapping("/payment-queue2")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> triggerPaymentQueue2() {
        return ResponseEntity.ok("payment queue");
    }
}
