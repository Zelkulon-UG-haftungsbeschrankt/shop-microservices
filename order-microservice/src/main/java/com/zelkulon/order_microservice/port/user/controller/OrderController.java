/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for order-microservice
 * path to this file is: src/main/java/com/zelkulon/order_microservice/port/user/controller
 **/
package com.zelkulon.order_microservice.port.user.controller;


import com.zelkulon.order_microservice.core.domain.model.Order;
import com.zelkulon.order_microservice.core.domain.model.OrderRegistry;
import com.zelkulon.order_microservice.core.domain.services.exception.NotFoundByOrderIdException;
import com.zelkulon.order_microservice.core.domain.services.interfaces.IOrderService;
import com.zelkulon.order_microservice.port.rabbitProducer.OrderProducer;
import com.zelkulon.order_microservice.port.user.exception.OrderNotFoundException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/v1")
@RestController
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService orderService;
    @Autowired
    private OrderProducer orderProducer;

    @PostMapping(path = "/order")
    public @ResponseBody Order createOrder(@RequestBody Order order, @RequestParam String username) {
        Order createOrder = orderService.createOrder(order,username, new Date());
        orderProducer.sendToAll(createOrder);
        return createOrder;
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable("id") UUID id) throws OrderNotFoundException {
        Order order = null;
        try{
            order = orderService.getOrderByOrderId(id);
        } catch (NotFoundByOrderIdException e) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<OrderRegistry> findByUsername(@PathVariable("username") String username){
        return orderService.getOrderRegistryByUsername(username);
    }

    public String getUsernameFromRequestHeader(String authorizationHeader) {
        String accessToken = authorizationHeader.split(" ",2)[1];
        String payloadEncoded = accessToken.split("\\.")[1];
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payloadDecoded = new String(decoder.decode(payloadEncoded));
        JSONObject jsonObject = new JSONObject(payloadDecoded);

        return jsonObject.getString("preferred_username");
    }
}
