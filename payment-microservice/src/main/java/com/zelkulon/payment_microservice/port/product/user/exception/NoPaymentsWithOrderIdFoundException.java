/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/user/exception
 **/
package com.zelkulon.payment_microservice.port.product.user.exception;

import java.util.UUID;

public class NoPaymentsWithOrderIdFoundException extends RuntimeException {
    public NoPaymentsWithOrderIdFoundException(UUID orderId) {
        super("Payment with orderId " + orderId + " not found");
    }
}
