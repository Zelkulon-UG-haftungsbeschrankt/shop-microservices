/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/user/advice
 **/
package com.zelkulon.payment_microservice.port.product.user.advice;

import com.zelkulon.payment_microservice.port.product.user.exception.NoPaymentsWithOrderIdFoundException;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentIdNotFoundException;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PaymentNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(value = PaymentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String paymentNotFoundExceptionHandler(PaymentNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = NoPaymentsWithOrderIdFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String paymentNotFoundExceptionHandler(NoPaymentsWithOrderIdFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = PaymentIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String paymentIdNotFoundExceptionHandler(PaymentIdNotFoundException ex) {
        return ex.getMessage();
    }
}
