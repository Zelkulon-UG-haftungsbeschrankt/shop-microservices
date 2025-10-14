/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/user/advice
 **/
package com.zelkulon.payment_microservice.port.product.user.advice;

import com.zelkulon.payment_microservice.port.product.user.exception.PaymentWithOrderIdAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PaymentCannotBeCreatedAdvice {

    @ResponseBody
    @ExceptionHandler(value = PaymentWithOrderIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String prodictIdAlreadyExistsExceptionHandler(PaymentWithOrderIdAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
