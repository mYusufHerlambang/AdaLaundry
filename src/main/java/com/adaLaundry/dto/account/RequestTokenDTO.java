package com.adaLaundry.dto.account;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class RequestTokenDTO {

    @NotBlank(message="Username is required.")
    private String username;

    @NotBlank(message="Password is required.")
    private String password;
}
