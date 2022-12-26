package com.adaLaundry.repository;

import com.adaLaundry.dto.order.OrderGridDTO;
import com.adaLaundry.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT new com.adaLaundry.dto.order.OrderGridDTO(ord.entryDate, ord.pickUpDate, ord.payStatus, cus.fullName,
                pc.packageName, ord.orderStatus, ord.bill, ord.updatedBy)
            FROM Order ord
                JOIN ord.customer cus
                JOIN ord.packages pc
            WHERE cus.fullName LIKE %:name%
            """)
    Page<OrderGridDTO> findAllOrder(Pageable pageable,
                                    @Param("name") String name);
}
