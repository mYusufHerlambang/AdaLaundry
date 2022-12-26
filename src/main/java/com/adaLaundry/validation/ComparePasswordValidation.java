package com.adaLaundry.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ComparePasswordValidation implements ConstraintValidator<ComparePassword, Object> {
    private String password;
    private String passwordConfirmation;

    @Override
    public void initialize(ComparePassword constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.passwordConfirmation = constraintAnnotation.passwordConfirmation();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String inputPass = new BeanWrapperImpl(value).getPropertyValue(password).toString();
        String inputConfirmPass = new BeanWrapperImpl(value).getPropertyValue(passwordConfirmation).toString();

        return (inputPass.equals(inputConfirmPass));
    }
}
