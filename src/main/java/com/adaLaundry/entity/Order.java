package com.adaLaundry.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Column(name="CustomerId")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name="CustomerId", insertable=false, updatable=false)
    private Customer customer;

    @Column(name="PackageId")
    private Long packageId;

    @ManyToOne
    @JoinColumn(name="PackageId", insertable=false, updatable=false)
    private Packages packages;

    @Column(name="PaymentId")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name="PaymentId", insertable=false, updatable=false)
    private Payment payment;

    @Column(name = "Weight")
    private Double weight;

    @Column(name = "EntryDate")
    private LocalDate entryDate;

    @Column(name="PickUpDate")
    private LocalDate pickUpDate;

    @Column(name="PayStatus")
    private String payStatus;

    @Column(name="OrderStatus")
    private String orderStatus;

    @Column(name="Bill")
    private Double bill;

    @Column(name = "UpdatedBy")
    private String updatedBy;

    //to insert new order
    public Order(Long customerId, Long packageId, Long paymentId, Double weight, LocalDate entryDate, LocalDate pickUpDate, String payStatus, String orderStatus, Double bill) {
        this.customerId = customerId;
        this.packageId = packageId;
        this.paymentId = paymentId;
        this.weight = weight;
        this.entryDate = entryDate;
        this.pickUpDate = pickUpDate;
        this.payStatus = payStatus;
        this.orderStatus = orderStatus;
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", customer=" + customer +
                ", packageId=" + packageId +
                ", packages=" + packages +
                ", paymentId=" + paymentId +
                ", payment=" + payment +
                ", weight=" + weight +
                ", entryDate=" + entryDate +
                ", pickUpDate=" + pickUpDate +
                ", payStatus='" + payStatus + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", bill=" + bill +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
