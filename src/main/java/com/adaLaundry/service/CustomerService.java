package com.adaLaundry.service;

import com.adaLaundry.dto.customer.CustomerUpsertDTO;
import com.adaLaundry.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<Customer> getAllCustomer(Pageable pageable, String name);

    Customer insertNewCustomer(CustomerUpsertDTO upsertDTO);

    Customer getCustomerById(Long id);

    Customer updateCustomerById(CustomerUpsertDTO upsertDTO, Long id);
}
