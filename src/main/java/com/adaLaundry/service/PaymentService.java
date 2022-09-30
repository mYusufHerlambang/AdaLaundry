package com.adaLaundry.service;

import com.adaLaundry.dto.payment.PaymentUpsertDTO;
import com.adaLaundry.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    Page<Payment> getAllPayment(Pageable pageable);

    Payment getPaymentById(Long id);

    void deleteById(Long id);

    Payment insertNewPayment(PaymentUpsertDTO paymentUpsertDTO);

    Payment updatePayment(PaymentUpsertDTO paymentUpsertDTO, Long id);
}
