package com.adaLaundry.dto.payment;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class PaymentUpsertDTO {

    @NotBlank(message="Payment Name is required.")
    private String payment;
}
