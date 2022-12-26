package com.adaLaundry.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidation implements ConstraintValidator<Date, Object> {

    private String dateChecked;
    private String dateCheckedAfter;


    @Override
    public void initialize(Date constraintAnnotation) {
        this.dateChecked = constraintAnnotation.dateChecked();
        this.dateCheckedAfter = constraintAnnotation.dateCheckedAfter();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate dateCheckedValue = (LocalDate)(new BeanWrapperImpl(value).getPropertyValue(dateChecked));
        LocalDate dateCheckedAfterValue = (LocalDate)(new BeanWrapperImpl(value).getPropertyValue(dateCheckedAfter));

        if(dateCheckedValue == null || dateCheckedAfterValue == null) {
            return true;
        }

        return (dateCheckedValue.isBefore(dateCheckedAfterValue)||dateCheckedValue.equals(dateCheckedAfterValue));
    }
}
