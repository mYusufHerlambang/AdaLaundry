package com.adaLaundry.dto.order;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class OrderInsertDTO {

    @NotBlank(message="Full Name is required.")
    private String fullName;

    @NotBlank(message="Phone Number is required.")
    private String phone;

    @NotBlank(message="Address is required.")
    private String address;

    @NotBlank(message="Package Name is required.")
    private String packageName;

    @NotBlank(message="Weight is required.")
    private Double weight;

    @NotBlank(message="Pick Up Date is required.")
    private LocalDate pickUpDate;

    @NotBlank(message="Payment is required.")
    private String payment;

    @NotBlank(message="Payment Status is required.")
    private String payStatus;

}
