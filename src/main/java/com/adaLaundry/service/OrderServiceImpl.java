package com.adaLaundry.service;

import com.adaLaundry.dto.order.OrderGridDTO;
import com.adaLaundry.dto.order.OrderInsertDTO;
import com.adaLaundry.dto.order.OrderUpdateDTO;
import com.adaLaundry.entity.Customer;
import com.adaLaundry.entity.Order;
import com.adaLaundry.entity.Packages;
import com.adaLaundry.entity.Payment;
import com.adaLaundry.helper.Helper;
import com.adaLaundry.repository.CustomerRepository;
import com.adaLaundry.repository.OrderRepository;
import com.adaLaundry.repository.PackageRepository;
import com.adaLaundry.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public Page<OrderGridDTO> getAllOrder(Pageable pageable, String name) {

        Page<OrderGridDTO> orders = orderRepository.findAllOrder(pageable, name);

        return orders;
    }

    @Override
    public Order insertNewOrder(OrderInsertDTO insertDTO) {

        //find customerByNameAndPhone to get customerId
        String fullName = insertDTO.getFullName();
        String phone = insertDTO.getPhone();

        Customer customer = customerRepository.findByNameAndPhone(fullName, phone);
        Long customerId = customer.getId();

        //find packageByName to get packageId
        String packageName = insertDTO.getPackageName();

        Packages thePackage = packageRepository.findByName(packageName);
        Long packageId = thePackage.getId();

        //find paymentByName to get paymentId
        String paymentName = insertDTO.getPayment();

        Payment payment = paymentRepository.findByName(paymentName);
        Long paymentId = payment.getId();

        //insert new order
        Order newOrder = new Order(
                customerId,
                packageId,
                paymentId,
                insertDTO.getWeight(),
                LocalDate.now(),
                insertDTO.getPickUpDate(),
                insertDTO.getPayStatus(),
                "new",
                Helper.countBill(thePackage.getPrice(), insertDTO.getWeight())
        );

        //set customer address
        customer.setAddress(insertDTO.getAddress());

        orderRepository.save(newOrder);
        customerRepository.save(customer);

        return newOrder;
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        Order orderTemp = null;

        if (optionalOrder.isPresent()){
            orderTemp = optionalOrder.get();
        }

        return orderTemp;
    }

    @Override
    public Order updateOrderById(OrderUpdateDTO updateDTO, Long id, String admin) {

        Order orderById = getOrderById(id);

        if (orderById != null){

            orderById.setPayStatus(updateDTO.getPayStatus());
            orderById.setOrderStatus(updateDTO.getOrderStatus());
            orderById.setUpdatedBy(admin);
        }

        orderRepository.save(orderById);

        return orderById;
    }
}
