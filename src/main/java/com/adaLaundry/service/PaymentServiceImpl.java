package com.adaLaundry.service;

import com.adaLaundry.dto.payment.PaymentUpsertDTO;
import com.adaLaundry.entity.Payment;
import com.adaLaundry.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Page<Payment> getAllPayment(Pageable pageable) {

        Page<Payment> payments = paymentRepository.findAllPayment(pageable);

        return payments;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        Payment paymentTemp = null;
        if (paymentOptional.isPresent()){

            paymentTemp = paymentOptional.get();
        }
        return paymentTemp;
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment insertNewPayment(PaymentUpsertDTO paymentUpsertDTO) {

        Payment newPayment = new Payment(
                paymentUpsertDTO.getPayment()
        );

        paymentRepository.save(newPayment);
        return newPayment;
    }

    @Override
    public Payment updatePayment(PaymentUpsertDTO paymentUpsertDTO, Long id) {
        Payment paymentById = getPaymentById(id);

        if (paymentById != null){
            paymentById.setPayment(paymentUpsertDTO.getPayment());
        }

        paymentRepository.save(paymentById);

        return paymentById;
    }
}
