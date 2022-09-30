package com.adaLaundry.repository;

import com.adaLaundry.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
            SELECT cus
            FROM Customer cus
            WHERE cus.fullName LIKE %:name%
            """)
    Page<Customer> findAllCustomer(Pageable pageable,
                                   @Param("name") String name);

    @Query("""
            SELECT cus
            FROM Customer cus
            WHERE cus.fullName = :fullName
                AND cus.phone = :phone
            """)
    Customer findByNameAndPhone(String fullName, String phone);
}
