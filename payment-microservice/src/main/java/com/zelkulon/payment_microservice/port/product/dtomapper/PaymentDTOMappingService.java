/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/main/java/com/zelkulon/payment_microservice/port/product/dtomapper
 **/
package com.zelkulon.payment_microservice.port.product.dtomapper;

import com.zelkulon.payment_microservice.core.domain.model.Payment;
import com.zelkulon.payment_microservice.port.product.dto.PaymentDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentDTOMappingService {

    public PaymentDTO mapPaymentToPaymentDTO(Payment payment) {

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getId());
        paymentDTO.setOrderId(payment.getOrderId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setUsername(payment.getUsername());

        return paymentDTO;
    }

    public Payment mapPaymentDTOToPayment(PaymentDTO paymentDTO) {

        Payment payment = new Payment();
        payment.setId(paymentDTO.getPaymentId());
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus(paymentDTO.getStatus());
        payment.setUsername(paymentDTO.getUsername());
        return payment;
    }

}
