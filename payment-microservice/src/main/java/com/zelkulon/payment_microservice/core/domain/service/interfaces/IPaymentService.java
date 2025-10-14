/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/core/domain/service/interfaces
 **/
package com.zelkulon.payment_microservice.core.domain.service.interfaces;

import com.zelkulon.payment_microservice.core.domain.model.Payment;
import com.zelkulon.payment_microservice.core.domain.model.PaymentStatus;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentIdNotFoundException;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentWithOrderIdAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface IPaymentService {
    Payment createPayment(Payment payment) throws PaymentWithOrderIdAlreadyExistsException;
    Payment updatePayment(Payment payment) throws PaymentIdNotFoundException;
    void deletePayment(UUID paymentId);
    Payment updatePaymentStatus(UUID paymentId, PaymentStatus paymentStatus) throws PaymentIdNotFoundException;

    List<Payment> getPaymentsByOrderId(UUID orderId);
}
