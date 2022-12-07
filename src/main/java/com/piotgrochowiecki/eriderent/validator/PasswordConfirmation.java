package com.piotgrochowiecki.eriderent.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmationValidator.class)
public @interface PasswordConfirmation {

    String message() default "{validation.error.passwordConfirmation}";

    String password();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
