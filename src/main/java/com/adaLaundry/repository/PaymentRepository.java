package com.adaLaundry.repository;

import com.adaLaundry.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("""
            SELECT py
            FROM Payment py
            """)
    Page<Payment> findAllPayment(Pageable pageable);

    @Query("""
            SELECT py
            FROM Payment py
            WHERE py.payment = :paymentName
            """)
    Payment findByName(String paymentName);
}
