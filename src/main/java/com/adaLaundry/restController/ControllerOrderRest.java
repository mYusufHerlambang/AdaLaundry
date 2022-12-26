package com.adaLaundry.restController;


import com.adaLaundry.dto.order.OrderGridDTO;
import com.adaLaundry.dto.order.OrderInsertDTO;
import com.adaLaundry.dto.order.OrderUpdateDTO;
import com.adaLaundry.entity.Order;
import com.adaLaundry.restExceptionHandler.InternalServerError;
import com.adaLaundry.restExceptionHandler.NotFoundException;
import com.adaLaundry.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class ControllerOrderRest {

    @Autowired
    private OrderService orderService;

    private final Integer maxRows = 10;

    @GetMapping("/index")
    public Object orderIndex(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "") String name,
                             @RequestParam(defaultValue = "") String payStatus){

        try {
            Pageable pageable = PageRequest.of(page - 1, maxRows, Sort.by("id"));

            Page<OrderGridDTO> grid = orderService.getAllOrder(pageable, name);

            List<OrderGridDTO> orderList = grid.stream().toList();

            if(payStatus.equals("")){
                return new ResponseEntity<>(grid, HttpStatus.OK);
            } else {
                // filter berdasarkan status lunas atau belum

                // cara stream
                List<OrderGridDTO> byPayStatusList = orderList.stream()
                        .filter(order -> order.getPayStatus().equals(payStatus))
                        .collect(Collectors.toList());


                // cara manual
                List<OrderGridDTO> listDariForeach = new ArrayList<>();

                for (OrderGridDTO order: grid) {
                    if(order.getPayStatus().equals(payStatus)){
                        listDariForeach.add(order);
                    }
                }

                return new ResponseEntity<>(listDariForeach, HttpStatus.OK);

//                return new ResponseEntity<>(byPayStatusList, HttpStatus.OK);
            }
        }catch (Exception e){
            throw new InternalServerError("There is a run-time error on the server.");
        }

    }

    @PostMapping("/add")
    public ResponseEntity<Object> insertOrder(@Valid @RequestBody OrderInsertDTO insertDTO){

        try {
            Order order = orderService.insertNewOrder(insertDTO);

            return new ResponseEntity<>(order, HttpStatus.CREATED);

        }catch (Exception e){
            throw new InternalServerError("There is a run-time error on the server.");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody OrderUpdateDTO updateDTO,
                                             @RequestParam(required = true)Long id,
                                             HttpServletRequest request){
        try {

            String admin = request.getUserPrincipal().getName();

            Order orderById = orderService.getOrderById(id);

            if (orderById != null){
                Order updateOrder = orderService.updateOrderById( updateDTO, id, admin);

                return new ResponseEntity<>(updateOrder, HttpStatus.ACCEPTED);
            }else{
                throw new NotFoundException("Customer with Id " + id + " Not Found!");
            }
        }catch (Exception e){
            throw new InternalServerError("There is a run-time error on the server.");
        }
    }
}
