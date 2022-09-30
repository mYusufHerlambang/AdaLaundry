package com.adaLaundry.restController;

import com.adaLaundry.dto.payment.PaymentUpsertDTO;
import com.adaLaundry.entity.Payment;
import com.adaLaundry.restExceptionHandler.NotFoundException;
import com.adaLaundry.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment")
public class ControllerPaymentRest {

    @Autowired
    private PaymentService paymentService;

    private final Integer maxRows = 10;

    @GetMapping("/index")
    public ResponseEntity<Page<Payment>> paymentIndex(@RequestParam(defaultValue = "1") Integer page){

        Pageable pageable = PageRequest.of(page - 1, maxRows, Sort.by("id"));

        Page<Payment> paymentGrid = paymentService.getAllPayment(pageable);

        return new ResponseEntity<>(paymentGrid, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePayment(@RequestParam(required = true) Long id){
        Payment paymentById = paymentService.getPaymentById(id);

        if(paymentById == null){
            throw new NotFoundException("Payment with Id " + id + " Not Found!");
        } else{
            paymentService.deleteById(id);
            return new ResponseEntity<>("Success Delete Payment With Name " + paymentById.getPayment(), HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> insertPayment(@Valid @RequestBody PaymentUpsertDTO paymentUpsertDTO){

        Payment payment = paymentService.insertNewPayment(paymentUpsertDTO);

        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Payment> updatePayment(@RequestBody PaymentUpsertDTO paymentUpsertDTO,
                                                  @RequestParam(required = true)Long id){

        Payment paymentById = paymentService.getPaymentById(id);

        if (paymentById != null){
            Payment updatePayment = paymentService.updatePayment( paymentUpsertDTO, id);

            return new ResponseEntity<>(updatePayment, HttpStatus.ACCEPTED);
        }else{

            throw new NotFoundException("Payment with Id " + id+ " Not Found!");
        }


    }
}
