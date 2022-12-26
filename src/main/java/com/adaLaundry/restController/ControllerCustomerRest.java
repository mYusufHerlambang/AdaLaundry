package com.adaLaundry.restController;

import com.adaLaundry.dto.customer.CustomerUpsertDTO;
import com.adaLaundry.dto.packages.PackageUpsertDTO;
import com.adaLaundry.entity.Customer;
import com.adaLaundry.entity.Packages;
import com.adaLaundry.restExceptionHandler.InternalServerError;
import com.adaLaundry.restExceptionHandler.NotFoundException;
import com.adaLaundry.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class ControllerCustomerRest {

    @Autowired
    private CustomerService customerService;

    private final Integer maxRows = 10;

    @GetMapping("/index")
    public ResponseEntity<Page<Customer>> customerIndex(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "") String name){
        try {
            Pageable pageable = PageRequest.of(page - 1, maxRows, Sort.by("id"));

            Page<Customer> grid = customerService.getAllCustomer(pageable, name);

            return new ResponseEntity<>(grid, HttpStatus.OK);

        }catch (Exception e){
            throw new InternalServerError("There is a run-time error on the server.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> insertCustomer(@Valid @RequestBody CustomerUpsertDTO upsertDTO){
        try {
            Customer customer = customerService.insertNewCustomer(upsertDTO);

            return new ResponseEntity<>(customer, HttpStatus.CREATED);

        }catch (Exception e){
            throw new InternalServerError("There is a run-time error on the server.");
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerUpsertDTO upsertDTO,
                                                  @RequestParam(required = true)Long id){

        try {
            Customer customerById = customerService.getCustomerById(id);

            if (customerById != null){
                Customer customerUpdate = customerService.updateCustomerById( upsertDTO, id);

                return new ResponseEntity<>(customerUpdate, HttpStatus.ACCEPTED);
            }else{

                throw new NotFoundException("Customer with Id " + id + " Not Found!");
            }
        }catch (Exception e){
            throw new InternalServerError("There is a run-time error on the server.");
        }

    }
}
