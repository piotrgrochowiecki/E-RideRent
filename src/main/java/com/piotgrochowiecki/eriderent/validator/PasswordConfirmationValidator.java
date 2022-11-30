package com.piotgrochowiecki.eriderent.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, String> {

    private String password;

    @Override
    public void initialize(PasswordConfirmation constraintAnnotation) {
        this.password = constraintAnnotation.password();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return password.equals(value);
    }
}
