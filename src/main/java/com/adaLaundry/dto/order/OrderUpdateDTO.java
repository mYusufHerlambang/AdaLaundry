package com.adaLaundry.dto.order;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class OrderUpdateDTO {

    @NotBlank(message="Payment Status is required.")
    private String payStatus;

    @NotBlank(message="Order is required.")
    private String orderStatus;
}
