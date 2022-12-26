package com.adaLaundry.dto.account;

import com.adaLaundry.validation.ComparePassword;
import com.adaLaundry.validation.UniqueUsername;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ComparePassword(message = "confirm password must be equals with password", password = "password", passwordConfirmation = "passwordConfirmation")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class AdminInsertDTO {

    @UniqueUsername(message = "Username already exist, please create another username")
    @NotBlank(message="Username is required.")
    @Size(max=30, message="Username can't be more than 30 characters.")
    private String username;

    @NotBlank(message="Password is required.")
    private String password;

    @NotBlank(message="Confirm Password is required.")
    private String passwordConfirmation;
}
