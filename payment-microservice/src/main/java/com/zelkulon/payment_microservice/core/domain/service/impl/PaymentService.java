/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/core/domain/service/impl
 **/
package com.zelkulon.payment_microservice.core.domain.service.impl;

import com.zelkulon.payment_microservice.core.domain.model.Payment;
import com.zelkulon.payment_microservice.core.domain.model.PaymentStatus;
import com.zelkulon.payment_microservice.core.domain.service.interfaces.IPaymentRepository;
import com.zelkulon.payment_microservice.core.domain.service.interfaces.IPaymentService;
import com.zelkulon.payment_microservice.port.product.user.exception.NoPaymentsWithOrderIdFoundException;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentIdNotFoundException;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentNotFoundException;
import com.zelkulon.payment_microservice.port.product.user.exception.PaymentWithOrderIdAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) throws PaymentWithOrderIdAlreadyExistsException {
        PaymentValidator.validate(payment);
        if(!paymentRepository.findByOrderId(payment.getOrderId()).isEmpty())
            throw new PaymentWithOrderIdAlreadyExistsException(payment.getOrderId());
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) throws PaymentIdNotFoundException {
        if(paymentRepository.existsById(payment.getId())){
            return paymentRepository.save(payment);
        }else {
            throw new PaymentIdNotFoundException(payment.getId());
        }
    }

    @Override
    public void deletePayment(UUID paymentId) throws PaymentIdNotFoundException {
        paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentIdNotFoundException(paymentId));
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public Payment updatePaymentStatus(UUID paymentId, PaymentStatus newPaymentStatus) throws PaymentIdNotFoundException {
        if(newPaymentStatus == null) throw new IllegalArgumentException("Paymentstatus is missing or invalid");

        if(paymentId == null) throw new IllegalArgumentException("Payment ID is missing or invalid");

        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentIdNotFoundException(paymentId));
        payment.setStatus(newPaymentStatus);

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByOrderId(UUID orderId) {
        if(orderId == null) throw new IllegalArgumentException("Order ID is missing or invalid");

        List<Payment> payments = paymentRepository.findByOrderId(orderId);

        if(payments == null || payments.isEmpty()) throw new NoPaymentsWithOrderIdFoundException(orderId);

        return payments;
    }
}
