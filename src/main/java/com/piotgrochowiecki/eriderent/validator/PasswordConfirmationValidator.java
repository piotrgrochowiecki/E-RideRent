package com.piotgrochowiecki.eriderent.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, Object> {

    private String password;
    private String matchingPassword;

    @Override
    public void initialize(PasswordConfirmation constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.matchingPassword = constraintAnnotation.matchingPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object matchingPasswordValue = new BeanWrapperImpl(value).getPropertyValue(matchingPassword);

        if (passwordValue != null) {
            return passwordValue.equals(matchingPasswordValue);
        } else {
            return matchingPasswordValue == null;
        }
    }
}
