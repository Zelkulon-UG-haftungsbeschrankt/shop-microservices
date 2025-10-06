/**
 * created on 06.10.25
 * by hdi10 with IntelliJ IDEA
 * for cart-microservice
 * path to this file is: src/main/java/com/zelkulon/cart_microservice/port/user/exception
 **/
package com.zelkulon.cart_microservice.port.user.exception;

import com.zelkulon.cart_microservice.core.domain.services.exception.ItemNotFoundException;

public class NoSuchItemExistsException extends Throwable {
    public NoSuchItemExistsException(ItemNotFoundException e) {}
}
