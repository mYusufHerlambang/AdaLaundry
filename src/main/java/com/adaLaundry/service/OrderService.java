package com.adaLaundry.service;

import com.adaLaundry.dto.order.OrderGridDTO;
import com.adaLaundry.dto.order.OrderInsertDTO;
import com.adaLaundry.dto.order.OrderUpdateDTO;
import com.adaLaundry.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderGridDTO> getAllOrder(Pageable pageable, String name);

    Order insertNewOrder(OrderInsertDTO insertDTO);

    Order getOrderById(Long id);

    Order updateOrderById(OrderUpdateDTO updateDTO, Long id, String admin);
}
