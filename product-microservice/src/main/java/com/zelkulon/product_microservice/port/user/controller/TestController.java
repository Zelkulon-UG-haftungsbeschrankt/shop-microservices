/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for product-microservice
 * path to this file is: src/main/java/com/zelkulon/product_microservice/port/user/controller
 **/
package com.zelkulon.product_microservice.port.user.controller;

import com.zelkulon.product_microservice.port.shoppingcart.producer.UpdateProductProducer;
import com.zelkulon.product_microservice.port.user.producer.ProductProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    ProductProducer productProducer;

    @Autowired
    UpdateProductProducer updateProductProducer;

    @GetMapping("/product-queue")
    public void triggerProductQueue() {
        try{
            productProducer.sendMessage("product");
        }catch (Exception e){

        }
    }

    @GetMapping("/product-update-queue")
    public void triggerProductUpdateQueue() {
        try{
            updateProductProducer.sendMessage("update");
        }catch (Exception e){

        }
    }

}
