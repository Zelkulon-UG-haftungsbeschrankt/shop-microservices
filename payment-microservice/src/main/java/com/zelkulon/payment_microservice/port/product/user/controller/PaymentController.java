/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/user/controller
 **/
package com.zelkulon.payment_microservice.port.product.user.controller;

import com.zelkulon.payment_microservice.core.domain.model.Payment;
import com.zelkulon.payment_microservice.core.domain.model.PaymentStatus;
import com.zelkulon.payment_microservice.core.domain.service.interfaces.IPaymentService;
import com.zelkulon.payment_microservice.port.product.user.exception.NoPaymentsWithOrderIdFoundException;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentIdNotFoundException;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentWithOrderIdAlreadyExistsException;
import com.zelkulon.payment_microservice.port.product.user.producer.PaymentProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;

    private PaymentProducer paymentProducer;

    @PostMapping(path = "/payment")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Payment createPayment(@RequestBody Payment payment) throws PaymentWithOrderIdAlreadyExistsException {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/payment/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Payment> getPaymentsByOrderId(@PathVariable("orderId") UUID orderId) throws NoPaymentsWithOrderIdFoundException {
        return paymentService.getPaymentsByOrderId(orderId);
    }

    @PutMapping(path = "/paymentStatus/{paymentId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Payment updatePaymentStatus (@PathVariable("paymentId") UUID paymentId, @RequestBody PaymentStatus newStatus) throws PaymentIdNotFoundException {
        return paymentService.updatePaymentStatus(paymentId, newStatus);
    }
}
