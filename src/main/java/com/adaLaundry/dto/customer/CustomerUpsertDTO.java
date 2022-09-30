package com.adaLaundry.dto.customer;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class CustomerUpsertDTO {

    @NotBlank(message="Full Name is required.")
    private String fullName;

    @NotBlank(message="Address is required.")
    private String address;

    @NotBlank(message="Phone Number is required.")
    private String phone;

    @NotBlank(message="Gender is required.")
    private String gender;
}
