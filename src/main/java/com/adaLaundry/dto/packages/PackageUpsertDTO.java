package com.adaLaundry.dto.packages;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class PackageUpsertDTO {

    @NotBlank(message="Package Name is required.")
    private String packageName;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be a positive number & bigger than 0")
    private Double price;
}
