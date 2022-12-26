package com.adaLaundry.dto.order;

import com.adaLaundry.validation.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Date(message ="You can't choose pick up date before today", dateChecked = "today", dateCheckedAfter = "pickUpDate")
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

    @NotNull(message = "Weight must not be null")
    @Positive(message = "Weight must be a positive number & bigger than 0")
    private Double weight;

    @NotNull(message="Pick Up Date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotBlank(message="Payment is required.")
    private String payment;

    @NotBlank(message="Payment Status is required.")
    private String payStatus;

    @JsonIgnore
    private LocalDate today = LocalDate.now();

}
