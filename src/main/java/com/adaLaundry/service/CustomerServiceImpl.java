package com.adaLaundry.service;

import com.adaLaundry.dto.customer.CustomerUpsertDTO;
import com.adaLaundry.entity.Customer;
import com.adaLaundry.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAllCustomer(Pageable pageable, String name) {

        Page<Customer> customers = customerRepository.findAllCustomer(pageable, name);

        return customers;
    }

    @Override
    public Customer insertNewCustomer(CustomerUpsertDTO upsertDTO) {

        Customer newCustomer = new Customer(
                upsertDTO.getFullName(),
                upsertDTO.getAddress(),
                upsertDTO.getPhone(),
                upsertDTO.getGender()
        );

        customerRepository.save(newCustomer);

        return newCustomer;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        Customer customerTemp = null;

        if (customerOptional.isPresent()){
            customerTemp = customerOptional.get();
        }

        return customerTemp;
    }

    @Override
    public Customer updateCustomerById(CustomerUpsertDTO upsertDTO, Long id) {

        Customer customerUpdate = getCustomerById(id);

        if (customerUpdate != null){
            customerUpdate.setFullName(upsertDTO.getFullName());
            customerUpdate.setAddress(upsertDTO.getAddress());
            customerUpdate.setPhone(upsertDTO.getPhone());
            customerUpdate.setGender(upsertDTO.getGender());
        }

        customerRepository.save(customerUpdate);

        return customerUpdate;
    }
}
