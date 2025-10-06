/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/user/advice
 **/
package com.zelkulon.cart_microservice.port.user.advice;

import com.zelkulon.cart_microservice.port.user.exception.AmountCannotBeLessThanZeroException;
import com.zelkulon.cart_microservice.port.user.exception.NoSuchItemExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CartAdvice {
    @ResponseBody
    @ExceptionHandler(value = NoSuchItemExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String itemNotFoundAdvice(NoSuchItemExistsException ex){
        return "Item does not exist";
    }

    @ResponseBody
    @ExceptionHandler(value = AmountCannotBeLessThanZeroException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String amountCannotBeLessThanZeroAdvice(AmountCannotBeLessThanZeroException ex){
        return "Amount cannot be less than 0.";
    }
}
