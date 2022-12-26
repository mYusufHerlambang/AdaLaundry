package com.adaLaundry.dto.account;

import com.adaLaundry.validation.ComparePassword;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ComparePassword(message = "confirm password must be equals with password", password = "password", passwordConfirmation = "passwordConfirmation")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class AdminUpdateDTO {

    @NotBlank(message="Password is required.")
    private String password;

    @NotBlank(message="Confirm Password is required.")
    private String passwordConfirmation;
}
