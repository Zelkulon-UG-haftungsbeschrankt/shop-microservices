/**
 * created on 14.10.25
 * by hdi10 with IntelliJ IDEA
 * for payment-microservice
 * path to this file is: src/test/java/com/zelkulon/payment_microservice/unittests
 * mixed testing, TDD, BDT
 **/
package com.zelkulon.payment_microservice.unittests;


import com.zelkulon.payment_microservice.core.domain.model.Payment;
import com.zelkulon.payment_microservice.core.domain.model.PaymentMethod;
import com.zelkulon.payment_microservice.core.domain.service.impl.PaymentService;
import com.zelkulon.payment_microservice.core.domain.service.interfaces.IPaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {

    @Mock
    IPaymentRepository mockPaymentRepository;

    @InjectMocks
    PaymentService paymentService;

    @Test
    public void createValidPaymentTest(){
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID());
        payment.setOrderId(UUID.randomUUID());
        payment.setAmount(BigDecimal.valueOf(100.0));
        payment.setMethod(PaymentMethod.AUF_RECHNUNG);

        when(mockPaymentRepository.existsById(payment.getId())).thenReturn(false);
        when(mockPaymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.createPayment(payment);
        verify(mockPaymentRepository,times(1)).save(payment);
        assertEquals(payment, savedPayment);

    }

}
