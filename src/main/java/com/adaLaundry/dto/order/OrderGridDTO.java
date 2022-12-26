package com.adaLaundry.dto.order;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class OrderGridDTO {

    private LocalDate entryDate;

    private LocalDate pickUpDateMin;

    private String payStatus;

    private String fullName;

    private String packageName;

    private String orderStatus;

    private Double bill;

    private String updatedBy;

}
