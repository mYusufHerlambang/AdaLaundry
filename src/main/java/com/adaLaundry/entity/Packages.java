package com.adaLaundry.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "Packages")
public class Packages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Column(name="PackageName")
    private String packageName;

    @Column(name="Price")
    private Double price;

    // to insert new package
    public Packages(String packageName, Double price) {
        this.packageName = packageName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Packages{" +
                "id=" + id +
                ", packageName='" + packageName + '\'' +
                ", price=" + price +
                '}';
    }
}
