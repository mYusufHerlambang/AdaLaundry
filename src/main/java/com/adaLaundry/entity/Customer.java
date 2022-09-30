package com.adaLaundry.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Column(name="FullName")
    private String fullName;

    @Column(name="Address")
    private String address;

    @Column(name="Phone")
    private String phone;

    @Column(name="Gender")
    private String gender;

    public Customer(String fullName, String address, String phone, String gender) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }
}
